package polkauction.core.service

import com.google.common.collect.ImmutableList
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import polkauction.core.model.dto.sidecar.getTimeStamp
import polkauction.core.model.entities.LeasePeriod
import polkauction.core.repository.ILeasePeriodRepository
import polkauction.core.service.sidecar.ISidecarClient
import polkauction.core.service.sidecar.ISidecarClientFactory

class LeasePeriodService(
    private val leasePeriodRepository: ILeasePeriodRepository,
    val sidecarClientFactory: ISidecarClientFactory
) : ILeasePeriodService {
    override suspend fun getAllFor(relayChain: String): ImmutableList<LeasePeriod> {

        val relayChainCapitalized = relayChain.toLowerCase().capitalize()
        val sidecarClient = sidecarClientFactory.getSidecarClient(relayChain)

        var leasePeriods = leasePeriodRepository.getAllFor(relayChainCapitalized)
        coroutineScope {
            leasePeriods = leasePeriods.map {
                async {
                    getLeasePeriodDetails(sidecarClient, it)
                }
            }.awaitAll()
        }

        return ImmutableList.copyOf(leasePeriods)
    }

    private suspend fun getLeasePeriodDetails(
        sidecarClient: ISidecarClient,
        it: LeasePeriod
    ): LeasePeriod {
        var blockStart = sidecarClient.getBlockAt(it.blockStart)
        var blockEnd = sidecarClient.getBlockAt(it.blockEnd)
        return it.copy(startTimeStamp = blockStart.getTimeStamp(), endTimeStamp = blockEnd.getTimeStamp())
    }
}