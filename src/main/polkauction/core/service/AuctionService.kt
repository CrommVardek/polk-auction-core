package polkauction.core.service

import polkauction.core.model.Auction
import polkauction.core.model.mapper.toAuction
import polkauction.core.service.sidecar.getSidecarClient

class AuctionService: IAuctionService {

    override suspend fun getCurrentAuction(chain: String): Auction {
        val sidecarClient = getSidecarClient(chain);
        return sidecarClient.getAuction().toAuction()
    }
}