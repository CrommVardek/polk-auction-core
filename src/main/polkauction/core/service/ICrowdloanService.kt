package polkauction.core.service

import polkauction.core.model.CrowdloanExtended

interface ICrowdloanService {
    suspend fun getCurrentCrowdloan(chain: String): CrowdloanExtended
}
