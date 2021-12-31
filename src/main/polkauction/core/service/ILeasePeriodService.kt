package polkauction.core.service

import com.google.common.collect.ImmutableList
import polkauction.core.model.entities.LeasePeriod

interface ILeasePeriodService {
    suspend fun getAllFor(relayChain: String) : ImmutableList<LeasePeriod>
    suspend fun getFilteredFor(relayChain: String, filter: (LeasePeriod) -> Boolean) : ImmutableList<LeasePeriod>
}