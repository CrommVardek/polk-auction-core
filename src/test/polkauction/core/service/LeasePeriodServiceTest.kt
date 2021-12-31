package polkauction.core.service

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import polkauction.core.model.dto.sidecar.ArgsDto
import polkauction.core.model.dto.sidecar.BlockDto
import polkauction.core.model.dto.sidecar.ExtrinsicDto
import polkauction.core.model.dto.sidecar.MethodDto
import polkauction.core.model.entities.LeasePeriod
import polkauction.core.model.entities.RelayChain
import polkauction.core.repository.ILeasePeriodRepository
import polkauction.core.service.sidecar.ISidecarClient
import polkauction.core.service.sidecar.ISidecarClientFactory

class LeasePeriodServiceTest {


    private val sidecarClientFactory = mockk<ISidecarClientFactory>()
    private val leasePeriodRepository = mockk<ILeasePeriodRepository>()
    private val sidecarClient = mockk<ISidecarClient>()
    private val leasePeriodService = LeasePeriodService(leasePeriodRepository, sidecarClientFactory)

    @Test
    fun `get all from relayChain should retrieve from DB and add timestamps`() {

        val relayChain = "Kusama"
        val kusamaRelayChain = RelayChain(1, "Kusama")
        val blockHead = BlockDto(10000000, listOf(ExtrinsicDto(MethodDto("timestamp"), ArgsDto("1636345932"))))

        val timeStampBlock9000000 = "1630294374"
        val timeStampBlock9604800 = "1633939242"
        val blocks = listOf(
            BlockDto(9000000, listOf(ExtrinsicDto(MethodDto("timestamp"), ArgsDto(timeStampBlock9000000)))),
            BlockDto(9604800, listOf(ExtrinsicDto(MethodDto("timestamp"), ArgsDto(timeStampBlock9604800))))
        )
        // One completely before the blockHead, one during, one in the future
        val leasePeriods = listOf(
            LeasePeriod(1, kusamaRelayChain, 9000000, 9604800),
            LeasePeriod(2, kusamaRelayChain, 9604800, 10209600),
            LeasePeriod(3, kusamaRelayChain, 10209600, 10814400),
        )

        every { sidecarClientFactory.getSidecarClient(relayChain) } returns sidecarClient
        coEvery { sidecarClient.getBlockAt(leasePeriods[0].blockNumberStart) } returns blocks.find { it.number == leasePeriods[0].blockNumberStart }!!
        coEvery { sidecarClient.getBlockAt(leasePeriods[0].blockNumberEnd) } returns blocks.find { it.number == leasePeriods[0].blockNumberEnd }!!
        coEvery { sidecarClient.getBlockAt(leasePeriods[1].blockNumberStart) } returns blocks.find { it.number == leasePeriods[1].blockNumberStart }!!
        coEvery { sidecarClient.getBlockHead() } returns blockHead
        coEvery { leasePeriodRepository.getAllFor(relayChain) } returns leasePeriods

        val result = runBlocking { leasePeriodService.getAllFor(relayChain) }

        assertEquals(3, result.size)
        assert(result.all { it.startTimeStamp != null })
        assert(result.all { it.endTimeStamp != null })
        assert(result.find{it.period == 1 }!!.startTimeStamp == timeStampBlock9000000.toLong())
        assert(result.find{it.period == 1 }!!.endTimeStamp == timeStampBlock9604800.toLong())
        assert(result.find{it.period == 2 }!!.startTimeStamp == timeStampBlock9604800.toLong())
    }

    @Test
    fun `get filtered from relayChain should retrieve only those needed and add timestamps`() {

        val relayChain = "Kusama"
        val kusamaRelayChain = RelayChain(1, "Kusama")
        val blockHead = BlockDto(10000000, listOf(ExtrinsicDto(MethodDto("timestamp"), ArgsDto("1636345932"))))

        val timeStampBlock9000000 = "1630294374"
        val timeStampBlock9604800 = "1633939242"
        val blocks = listOf(
            BlockDto(9000000, listOf(ExtrinsicDto(MethodDto("timestamp"), ArgsDto(timeStampBlock9000000)))),
            BlockDto(9604800, listOf(ExtrinsicDto(MethodDto("timestamp"), ArgsDto(timeStampBlock9604800))))
        )
        // One completely before the blockHead, one during, one in the future
        val leasePeriods = listOf(
            LeasePeriod(1, kusamaRelayChain, 9000000, 9604800),
            LeasePeriod(2, kusamaRelayChain, 9604800, 10209600),
            LeasePeriod(3, kusamaRelayChain, 10209600, 10814400),
        )

        every { sidecarClientFactory.getSidecarClient(relayChain) } returns sidecarClient
        coEvery { sidecarClient.getBlockAt(leasePeriods[0].blockNumberStart) } returns blocks.find { it.number == leasePeriods[0].blockNumberStart }!!
        coEvery { sidecarClient.getBlockAt(leasePeriods[0].blockNumberEnd) } returns blocks.find { it.number == leasePeriods[0].blockNumberEnd }!!
        coEvery { sidecarClient.getBlockAt(leasePeriods[1].blockNumberStart) } returns blocks.find { it.number == leasePeriods[1].blockNumberStart }!!
        coEvery { sidecarClient.getBlockHead() } returns blockHead
        coEvery { leasePeriodRepository.getAllFor(relayChain) } returns leasePeriods

        val filter: (LeasePeriod) -> Boolean = { it.period >= 2 }

        val result = runBlocking { leasePeriodService.getFilteredFor(relayChain, filter) }

        assertEquals(2, result.size)
        assert(result.all { it.startTimeStamp != null })
        assert(result.all { it.endTimeStamp != null })
        assert(result.find{it.period == 2 }!!.startTimeStamp == timeStampBlock9604800.toLong())
        assert(result.all(filter))
    }
}