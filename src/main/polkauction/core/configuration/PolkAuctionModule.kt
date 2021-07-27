package polkauction.core.configuration

import org.koin.dsl.module
import polkauction.core.service.CrowdloanService
import polkauction.core.service.ICrowdloanService

val polkAuctionCoreModule = module(createdAtStart = true) {
    single { CrowdloanService(get()) }
}