package polkauction.core.service

import polkauction.core.model.AuctionExtended
import polkauction.core.model.dto.sidecar.AuctionDto
import polkauction.core.model.extends
import polkauction.core.model.mapper.toAuction
import polkauction.core.repository.IParachainRepository
import polkauction.core.service.sidecar.ISidecarClientFactory

class AuctionService(
    private val parachainRepository: IParachainRepository,
    private val sidecarClientFactory: ISidecarClientFactory
) : IAuctionService {

    override suspend fun getCurrentAuction(chain: String): AuctionExtended {
        val registeredParachains = parachainRepository.getAllFor(chain.toLowerCase().capitalize())
        val sidecarClient = sidecarClientFactory.getSidecarClient(chain)
        var auctionRaw = sidecarClient.getAuction()
        auctionRaw.winning = auctionRaw.winning?.filter { it.bid != null }
        return auctionRaw.toAuction().extends(registeredParachains)
    }
}