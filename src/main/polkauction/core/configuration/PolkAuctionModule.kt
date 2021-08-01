package polkauction.core.configuration

import org.koin.dsl.module
import polkauction.core.repository.IParachainRepository
import polkauction.core.repository.IRelayChainRepository
import polkauction.core.repository.ParachainRepository
import polkauction.core.repository.RelayChainRepository
import polkauction.core.service.*

val polkAuctionCoreModule = module(createdAtStart = true) {
    // Services
    single<IAuctionService> { AuctionService() }
    single<ICrowdloanService> { CrowdloanService() }
    single<IParachainService> { ParachainService(get()) }
    single<IRuntimeService> { RuntimeService() }
    //Repositories
    single<IRelayChainRepository> { RelayChainRepository() }
    single<IParachainRepository> { ParachainRepository() }
}