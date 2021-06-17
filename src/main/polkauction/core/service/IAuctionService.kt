package polkauction.core.service

import polkauction.core.model.Auction

interface IAuctionService {
    suspend fun GetCurrentAuction(): Auction
}