package polkauction.core.route

import com.typesafe.config.ConfigFactory
import io.ktor.config.*
import io.ktor.http.*
import io.ktor.server.engine.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ParachainRoutingTest {

    private lateinit var testEnv : ApplicationEngineEnvironment

    @BeforeEach
    fun init() {
        testEnv = createTestEnvironment {
            config = HoconApplicationConfig(ConfigFactory.load("application.conf"))
        }
    }

    @Test
    fun incorrectChainParameterShouldReturnBadRequest() = withApplication(testEnv) {
        with(handleRequest(HttpMethod.Get, "/parachain/%uff0")) {
            assertEquals(HttpStatusCode.BadRequest, response.status())
        }
    }

    @Test
    fun noChainParameterShouldReturnNotFound() = withApplication(testEnv) {
        with(handleRequest(HttpMethod.Get, "/parachain/")) {
            assertEquals(HttpStatusCode.NotFound, response.status())
        }
    }

    @Test
    fun incorrectParachainIdParameterShouldReturnBadRequest() = withApplication(testEnv) {
        with(handleRequest(HttpMethod.Get, "/parachain/Kusama/notAnInt")) {
            assertEquals(HttpStatusCode.BadRequest, response.status())
        }
    }

}