package vardek.polkauction.core.service

import vardek.polkauction.core.model.Auction

interface IAuctionService {
    suspend fun GetCurrentAuction(): Auction
}