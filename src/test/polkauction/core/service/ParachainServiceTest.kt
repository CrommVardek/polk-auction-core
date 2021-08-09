package polkauction.core.service

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.jetbrains.kotlin.backend.common.atMostOne
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import polkauction.core.model.dto.sidecar.BlockIdentifiersDto
import polkauction.core.model.dto.sidecar.ParaDto
import polkauction.core.model.dto.sidecar.ParasDto
import polkauction.core.model.dto.sidecar.ParasLeaseInfoDto
import polkauction.core.model.entities.Parachain
import polkauction.core.model.entities.RelayChain
import polkauction.core.repository.IParachainRepository
import polkauction.core.service.sidecar.ISidecarClient
import polkauction.core.service.sidecar.ISidecarClientFactory

class ParachainServiceTest {

    @Test
    fun `retrieving parachains should map off-chain information from DB if they exists`() {
        val relayChain = "Kusama"
        val statemineChainName = "Statemine"
        val at = BlockIdentifiersDto(
            "0xdf4e4eb4fbcb139e14e3d64f4f96cd59a3784d06d46ca1ff013635a9a50c91bc",
            "8716792"
        )
        val statemineParachain = Parachain(
            1000,
            statemineChainName,
            RelayChain(2, "Kusama"),
            "statemine.network",
            ""
        )
        val onChainParachains =
            ParasDto(
                at, listOf(
                    ParaDto("1000", "Parachain"),
                    ParaDto("2001", "Parachain"),
                    ParaDto("2024", "Parathread")
                )
            )
        val registeredParachains = listOf(statemineParachain)
        val parachainRepository = mockk<IParachainRepository>()
        val sidecarClientFactory = mockk<ISidecarClientFactory>()
        val sidecarClient = mockk<ISidecarClient>()
        coEvery { parachainRepository.getAllFor(relayChain) } returns registeredParachains
        every { sidecarClientFactory.getSidecarClient(relayChain) } returns sidecarClient
        coEvery { sidecarClient.getParas() } returns onChainParachains
        coEvery { sidecarClient.getParaLeaseInfo(any()) } returns ParasLeaseInfoDto(at, "Parachain")

        val parachainService = ParachainService(parachainRepository, sidecarClientFactory)

        val result = runBlocking { parachainService.getAllCurrentParachains(relayChain) }

        assertEquals(3, result.size)
        assert(result.atMostOne { it.parachainName == statemineChainName } != null)
    }
}