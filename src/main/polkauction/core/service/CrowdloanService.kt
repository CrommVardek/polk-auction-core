package polkauction.core.service

import polkauction.core.model.Crowdloan
import polkauction.core.model.mapper.toCrowdloan
import polkauction.core.service.sidecar.ISidecarClient

class CrowdloanService(private val sidecarClient: ISidecarClient): ICrowdloanService {
    override suspend fun getCurrentCrowdloan(): Crowdloan
    {
        return sidecarClient.getCrowdloan().toCrowdloan()
    }
}