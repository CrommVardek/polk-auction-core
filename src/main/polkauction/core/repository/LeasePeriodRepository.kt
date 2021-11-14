package polkauction.core.repository

import polkauction.core.model.entities.LeasePeriod

class LeasePeriodRepository: ILeasePeriodRepository {
    override suspend fun getAllFor(relayChain: String): List<LeasePeriod> {
        TODO("Not yet implemented")
    }
}