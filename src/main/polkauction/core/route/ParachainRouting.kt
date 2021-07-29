package polkauction.core.route

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import polkauction.core.service.IAuctionService
import polkauction.core.service.IParachainService
import polkauction.core.service.ParachainService
import polkauction.core.service.sidecar.SidecarClient

fun Route.parachainRouting() {
    route("/parachain") {
        get("{chain}") {

            val chain = call.parameters["chain"] ?: return@get call.respondText(
                "Missing or malformed chain",
                status = HttpStatusCode.BadRequest
            )

            val parachainService: IParachainService by this@route.inject()

            val parachains = parachainService.getAllCurrentParachains(chain)

            call.respond(parachains)
        }
        get("{chain}/{id}") {

            val chain = call.parameters["chain"] ?: return@get call.respondText(
                "Missing or malformed chain",
                status = HttpStatusCode.BadRequest
            )
            val id = call.parameters["id"]?.toLongOrNull() ?: return@get call.respondText(
                "Missing or malformed id - only numbers allowed",
                status = HttpStatusCode.BadRequest
            )

            val parachainService: IParachainService by this@route.inject()
            val parachain = parachainService.getParachain(chain, id)
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