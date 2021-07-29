package polkauction.core.route

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import polkauction.core.service.ICrowdloanService

fun Route.crowdloanRouting() {
    route("/crowdloan") {
        get("{chain}") {

            val chain = call.parameters["chain"] ?: return@get call.respondText(
                "Missing or malformed chain",
                status = HttpStatusCode.BadRequest
            )

            val crowdloanService: ICrowdloanService by this@route.inject()
            call.respond(crowdloanService.getCurrentCrowdloan(chain))
        }
    }
}

fun Application.registerCrowdloanRoutes() {
    routing {
        crowdloanRouting()
    }
}