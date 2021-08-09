package polkauction.core.service

import polkauction.core.model.AuctionExtended
import polkauction.core.model.extends
import polkauction.core.model.mapper.toAuction
import polkauction.core.repository.IParachainRepository
import polkauction.core.service.sidecar.SidecarClientFactory

class AuctionService(
    private val parachainRepository: IParachainRepository,
    private val sidecarClientFactory: SidecarClientFactory
) : IAuctionService {

    override suspend fun getCurrentAuction(chain: String): AuctionExtended {
        val registeredParachains = parachainRepository.getAllFor(chain)
        val sidecarClient = sidecarClientFactory.getSidecarClient(chain);
        return sidecarClient.getAuction().toAuction().extends(registeredParachains)
    }
}