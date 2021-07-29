package polkauction.core.service

import polkauction.core.model.Crowdloan
import polkauction.core.model.mapper.toCrowdloan
import polkauction.core.service.sidecar.getSidecarClient

class CrowdloanService: ICrowdloanService {
    override suspend fun getCurrentCrowdloan(chain: String): Crowdloan
    {
        val sidecarClient = getSidecarClient(chain)
        return sidecarClient.getCrowdloan().toCrowdloan()
    }
}