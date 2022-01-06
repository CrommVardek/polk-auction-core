package polkauction.core.service

import com.google.common.collect.ImmutableList
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import polkauction.core.model.EcoSystemConstants.EXPECTED_BLOCK_TIME_MS
import polkauction.core.model.dto.sidecar.BlockDto
import polkauction.core.model.dto.sidecar.getTimeStamp
import polkauction.core.model.entities.LeasePeriod
import polkauction.core.repository.ILeasePeriodRepository
import polkauction.core.service.sidecar.ISidecarClient
import polkauction.core.service.sidecar.ISidecarClientFactory
import kotlin.math.abs

class LeasePeriodService(
    private val leasePeriodRepository: ILeasePeriodRepository,
    private val sidecarClientFactory: ISidecarClientFactory
) : ILeasePeriodService {
    override suspend fun getAllFor(relayChain: String): ImmutableList<LeasePeriod> {
        return getFilteredFor(relayChain) { true }
    }

    override suspend fun getFilteredFor(
        relayChain: String,
        filter: (LeasePeriod) -> Boolean
    ): ImmutableList<LeasePeriod> {
        val relayChainCapitalized = relayChain.toLowerCase().capitalize()
        val sidecarClient = sidecarClientFactory.getSidecarClient(relayChain)

        var leasePeriods = leasePeriodRepository.getAllFor(relayChainCapitalized).filter(filter)
        val blockHead = sidecarClient.getBlockHead()
        coroutineScope {
            leasePeriods = leasePeriods.map {
                async {
                    getLeasePeriodDetails(sidecarClient, blockHead, it)
                }
            }.awaitAll()
        }

        return ImmutableList.copyOf(leasePeriods)
    }

    private suspend fun getLeasePeriodDetails(
        sidecarClient: ISidecarClient,
        blockHead: BlockDto,
        it: LeasePeriod
    ): LeasePeriod {
        var startTimeStamp: Long? = if (it.blockNumberStart <= blockHead.number.toInt()) {
            var blockStart = sidecarClient.getBlockAt(it.blockNumberStart)
            blockStart.getTimeStamp()
        } else {
            getEstimatedTimeStampFromHead(it.blockNumberStart, blockHead)
        }
        var endTimeStamp: Long? = if (it.blockNumberEnd <= blockHead.number.toInt()) {
            var blockStart = sidecarClient.getBlockAt(it.blockNumberEnd)
            blockStart.getTimeStamp()
        } else {
            getEstimatedTimeStampFromHead(it.blockNumberEnd, blockHead)
        }
        return it.copy(startTimeStamp = startTimeStamp, endTimeStamp = endTimeStamp)
    }

    private fun getEstimatedTimeStampFromHead(
        blockHeight: Int,
        blockHead: BlockDto
    ): Long? {
        val heightDifference = abs(blockHeight - blockHead.number.toInt())
        return blockHead.getTimeStamp()?.plus(heightDifference * (EXPECTED_BLOCK_TIME_MS))
    }
}