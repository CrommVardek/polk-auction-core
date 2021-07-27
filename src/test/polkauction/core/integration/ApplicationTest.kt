package polkauction.core.integration

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import polkauction.core.module
import kotlin.test.*

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "/auction/kusama").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }
}
