package polkauction.core.configuration

import org.koin.dsl.module
import polkauction.core.repository.*
import polkauction.core.service.*
import polkauction.core.service.sidecar.ISidecarClientFactory
import polkauction.core.service.sidecar.SidecarClientFactory

val polkAuctionCoreModule = module(createdAtStart = true) {
    // Services
    single<ISidecarClientFactory> { SidecarClientFactory() }

    single<IAuctionService> { AuctionService(get(), get(), get()) }
    single<ICrowdloanService> { CrowdloanService(get(), get(), get()) }
    single<IParachainService> { ParachainService(get(), get(), get()) }
    single<IRuntimeService> { RuntimeService(get()) }
    single<ILeasePeriodService> { LeasePeriodService(get(), get()) }

    //Repositories
    single<IRelayChainRepository> { RelayChainRepository() }
    single<IParachainRepository> { ParachainRepository() }
    single<ILeasePeriodRepository> { LeasePeriodRepository() }
}