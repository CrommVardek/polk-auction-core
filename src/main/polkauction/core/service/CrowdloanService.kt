package polkauction.core.service

import polkauction.core.model.CrowdloanExtended
import polkauction.core.model.extends
import polkauction.core.model.mapper.toCrowdloan
import polkauction.core.repository.IParachainRepository
import polkauction.core.service.sidecar.ISidecarClientFactory

class CrowdloanService(
    private val parachainRepository: IParachainRepository,
    private val sidecarClientFactory: ISidecarClientFactory
) : ICrowdloanService {
    override suspend fun getCurrentCrowdloan(chain: String): CrowdloanExtended {
        val sidecarClient = sidecarClientFactory.getSidecarClient(chain)
        val parachainsEntities = parachainRepository.getAllFor(chain)
        return sidecarClient.getCrowdloan().toCrowdloan().extends(parachainsEntities)
    }
}