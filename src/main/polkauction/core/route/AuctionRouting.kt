package polkauction.core.route

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import polkauction.core.service.AuctionService
import polkauction.core.service.sidecar.SidecarClient

fun Route.auctionRouting() {
    route("/auction") {
        get("chain") {
            //TODO IoC
            val chain = call.parameters["chain"] ?: return@get call.respondText(
                "Missing or malformed chain",
                status = HttpStatusCode.BadRequest
            )
            val sidecarClient = SidecarClient(chain)
            val auctionService = AuctionService(sidecarClient)
            call.respond(auctionService.GetCurrentAuction())
        }
    }
}

fun Application.registerAuctionRoutes() {
    routing {
        auctionRouting()
    }
}