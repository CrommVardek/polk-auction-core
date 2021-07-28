package polkauction.core.configuration

import org.koin.dsl.module
import polkauction.core.service.*
import polkauction.core.service.sidecar.ISidecarClient
import polkauction.core.service.sidecar.SidecarClient

val polkAuctionCoreModule = module(createdAtStart = true) {
    // Client
    single<ISidecarClient> { (chain: String) -> SidecarClient(chain) }
    // Services
    single<IAuctionService> { (sidecarClient: ISidecarClient) -> AuctionService(sidecarClient) }
}