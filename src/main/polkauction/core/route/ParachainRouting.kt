package polkauction.core.route

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import polkauction.core.service.ParachainService
import polkauction.core.service.sidecar.SidecarClient

fun Route.parachainRouting() {
    route("/parachain") {
        get("{chain}") {
            //TODO IoC
            val chain = call.parameters["chain"] ?: return@get call.respondText(
                "Missing or malformed chain",
                status = HttpStatusCode.BadRequest
            )
            val sidecarClient = SidecarClient(chain)
            val parachainService = ParachainService(sidecarClient)

            val parachains = parachainService.getAllCurrentParachains()

            call.respond(parachains)
        }
        get("{chain}/{id}") {
            //TODO IoC
            val chain = call.parameters["chain"] ?: return@get call.respondText(
                "Missing or malformed chain",
                status = HttpStatusCode.BadRequest
            )
            val id = call.parameters["id"]?.toLongOrNull() ?: return@get call.respondText(
                "Missing or malformed id - only numbers allowed",
                status = HttpStatusCode.BadRequest
            )

            val sidecarClient = SidecarClient(chain)
            val parachainService = ParachainService(sidecarClient)
            val parachain = parachainService.getParachain(id)
                ?: return@get call.respondText(
                    "No parachain found with id $id on chain $chain",
                    status = HttpStatusCode.NotFound
                )

            call.respond(parachain)
        }
    }
}

fun Application.registerParachainRoutes() {
    routing {
        parachainRouting()
    }
}