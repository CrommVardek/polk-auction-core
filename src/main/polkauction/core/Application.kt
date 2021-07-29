package polkauction.core

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import org.koin.ktor.ext.Koin
import polkauction.core.configuration.polkAuctionCoreModule
import polkauction.core.route.registerAuctionRoutes
import polkauction.core.route.registerCrowdloanRoutes
import polkauction.core.route.registerParachainRoutes

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    /*install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        // header(HttpHeaders.Authorization)
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }*/

    install(DefaultHeaders) {
        header("X-Engine", "Ktor") // will send this header with each response
    }

    install(ContentNegotiation) {
        jackson()
    }

    install(Koin){
        modules(polkAuctionCoreModule)
    }

    registerParachainRoutes()

    registerAuctionRoutes()

    registerCrowdloanRoutes()

}

