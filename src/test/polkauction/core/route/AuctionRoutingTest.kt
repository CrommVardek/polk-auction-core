package polkauction.core.route

import com.typesafe.config.ConfigFactory
import io.ktor.config.*
import io.ktor.http.*
import io.ktor.server.engine.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AuctionRoutingTest {

    private lateinit var testEnv: ApplicationEngineEnvironment

    @BeforeEach
    fun init() {
        testEnv = createTestEnvironment {
            config = HoconApplicationConfig(ConfigFactory.load("application.conf"))
        }
    }

    @Test
    fun incorrectChainParameterShouldReturnBadRequest() = withApplication(testEnv) {
        with(handleRequest(HttpMethod.Get, "/auction/%uff0")) {
            Assertions.assertEquals(HttpStatusCode.BadRequest, response.status())
        }
    }

    @Test
    fun noChainParameterShouldReturnNotFound() = withApplication(testEnv) {
        with(handleRequest(HttpMethod.Get, "/auction")) {
            Assertions.assertEquals(HttpStatusCode.NotFound, response.status())
        }
    }

}