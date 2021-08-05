package polkauction.core.service

import polkauction.core.model.AuctionExtended

interface IAuctionService {
    suspend fun getCurrentAuction(chain : String): AuctionExtended
}