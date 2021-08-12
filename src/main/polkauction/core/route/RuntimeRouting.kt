package polkauction.core.route

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import polkauction.core.service.ICrowdloanService
import polkauction.core.service.IRuntimeService

fun Route.runtimeRouting() {
    route("/runtime") {
        get("{chain}") {

            val chain = call.parameters["chain"] ?: return@get call.respondText(
                "Missing or malformed chain",
                status = HttpStatusCode.BadRequest
            )

            val runtimeService: IRuntimeService by this@route.inject()
            call.respond(runtimeService.getSpecification(chain))
        }
    }
}

fun Application.registerRuntimeRoutes() {
    routing {
        runtimeRouting()
    }
}