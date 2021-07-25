package polkauction.core.service;

import polkauction.core.model.Crowdloan;

interface ICrowdloanService {
    suspend fun getCurrentCrowdloan(): Crowdloan
}
