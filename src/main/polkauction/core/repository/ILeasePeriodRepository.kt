package polkauction.core.repository

import polkauction.core.model.entities.LeasePeriod

interface ILeasePeriodRepository {
    suspend fun getAllFor(relayChain: String): List<LeasePeriod>
}