package polkauction.core.route

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.core.parameter.parametersOf
import org.koin.ktor.ext.inject
import polkauction.core.service.IAuctionService
import polkauction.core.service.sidecar.ISidecarClient

fun Route.auctionRouting() {
    route("/auction") {
        get("{chain}") {
            //TODO IoC
            val chain = call.parameters["chain"] ?: return@get call.respondText(
                "Missing or malformed chain",
                status = HttpStatusCode.BadRequest
            )
            val sidecarClient: ISidecarClient by this@route.inject { parametersOf(chain) }
            val auctionService: IAuctionService by this@route.inject() { parametersOf(sidecarClient) }
            call.respond(auctionService.getCurrentAuction())
        }
    }
}

fun Application.registerAuctionRoutes() {
    routing {
        auctionRouting()
    }
}