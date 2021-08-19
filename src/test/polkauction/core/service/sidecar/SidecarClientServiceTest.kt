package polkauction.core.service.sidecar

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import polkauction.core.exception.NoSuchChainException

class SidecarClientServiceTest {

    @Test
    fun initSidecarClientWithUnknownRelayChainShouldThrowNoSuchChainException() {
        val unknownChain = "Ethereum"
        Assertions.assertThrows(NoSuchChainException::class.java) {
            SidecarClient(unknownChain)
        }
    }
}