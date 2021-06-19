package polkauction.core.service

import polkauction.core.model.Auction
import polkauction.core.service.sidecar.ISidecarClient

class AuctionService(private val sidecarClient: ISidecarClient): IAuctionService {
    override suspend fun GetCurrentAuction(): Auction {
        return sidecarClient.GetAuction()
    }
}