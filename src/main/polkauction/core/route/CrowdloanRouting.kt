package polkauction.core.route

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import polkauction.core.service.CrowdloanService
import polkauction.core.service.sidecar.SidecarClient

//TODO Inject crowdloan service
fun Route.crowdloanRouting() {
    route("/crowdloan") {
        get("{chain}") {
            //TODO IoC
            val chain = call.parameters["chain"] ?: return@get call.respondText(
                "Missing or malformed chain",
                status = HttpStatusCode.BadRequest
            )
            val sidecarClient = SidecarClient(chain)
            val crowdloanService = CrowdloanService(sidecarClient)
            call.respond(crowdloanService.getCurrentCrowdloan())
        }
    }
}

fun Application.registerCrowdloanRoutes() {
    routing {
        crowdloanRouting()
    }
}