package vardek.polkauction.core.service

import vardek.polkauction.core.model.Auction
import vardek.polkauction.core.service.sidecar.ISidecarClient

class AuctionService(private val sidecarClient: ISidecarClient): IAuctionService {
    override suspend fun GetCurrentAuction(): Auction {
        return sidecarClient.GetAuction()
    }
}