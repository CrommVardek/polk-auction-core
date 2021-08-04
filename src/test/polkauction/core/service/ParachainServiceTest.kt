package polkauction.core.service

import io.mockk.coEvery
import io.mockk.mockk
import org.junit.jupiter.api.Test
import polkauction.core.model.entities.Parachain
import polkauction.core.model.entities.RelayChain
import polkauction.core.repository.IParachainRepository

class ParachainServiceTest {

    @Test
    suspend fun `retrieving parachains should map off-chain information from DB if they exists`  () {

        val statemineParachain = Parachain(
            1000,
            "Statemine",
            RelayChain(2, "Kusama"),
            "statemine.network",
            ""
        )
        val registerdParachains = listOf(statemineParachain)
        val parachainRepository = mockk<IParachainRepository>()
        coEvery { parachainRepository.getAllFor(any()) } returns registerdParachains

        val parachainService = ParachainService(parachainRepository)

        val result = parachainService.getAllCurrentParachains("Kusama")

        //TODO
    }
}