package polkauction.core.integration

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Ignore
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import polkauction.core.module

class ApplicationTest {
    @Ignore
    @Test
    fun testRoot() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "/auction/kusama").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }
}
