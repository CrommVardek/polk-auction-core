package vardek.polkauction.core.route

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import vardek.polkauction.core.service.ParachainService
import vardek.polkauction.core.service.sidecar.SidecarClient

fun Route.parachainRouting() {
    route("/parachain") {
        get {
            //TODO IoC
            val sidecarClient = SidecarClient()
            val parachainService = ParachainService(sidecarClient)
            call.respond(parachainService.GetAllCurrentParachains())
        }
    }
}

fun Application.registerParachainRoutes() {
    routing {
        parachainRouting()
    }
}