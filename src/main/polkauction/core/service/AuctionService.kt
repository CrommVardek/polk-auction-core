package polkauction.core.service

import polkauction.core.model.Auction
import polkauction.core.model.mapper.toAuction
import polkauction.core.repository.IParachainRepository
import polkauction.core.service.sidecar.getSidecarClient

class AuctionService(private val parachainRepository: IParachainRepository): IAuctionService {

    override suspend fun getCurrentAuction(chain: String): Auction {
        val registeredParachains = parachainRepository.getAllFor(chain)
        val sidecarClient = getSidecarClient(chain);
        return sidecarClient.getAuction().toAuction()
    }
}