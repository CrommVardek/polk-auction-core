package polkauction.core.configuration

import org.koin.dsl.module
import polkauction.core.service.*

val polkAuctionCoreModule = module(createdAtStart = true) {
    // Services
    single<IAuctionService> { AuctionService() }
    single<ICrowdloanService> { CrowdloanService() }
    single<IParachainService> { ParachainService() }
}