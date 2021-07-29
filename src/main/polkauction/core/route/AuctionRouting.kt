package polkauction.core.route

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import polkauction.core.service.IAuctionService

fun Route.auctionRouting() {
    route("/auction") {
        get("{chain}") {

            val chain = call.parameters["chain"] ?: return@get call.respondText(
                "Missing or malformed chain",
                status = HttpStatusCode.BadRequest
            )

            val auctionService: IAuctionService by this@route.inject()
            call.respond(auctionService.getCurrentAuction(chain))
        }
    }
}

fun Application.registerAuctionRoutes() {
    routing {
        auctionRouting()
    }
}