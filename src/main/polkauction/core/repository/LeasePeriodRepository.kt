package polkauction.core.repository

import org.jetbrains.exposed.sql.transactions.transaction
import polkauction.core.model.entities.*

class LeasePeriodRepository : ILeasePeriodRepository {
    override suspend fun getAllFor(relayChain: String): List<LeasePeriod> = transaction {
        return@transaction getAllForUnfiltered(relayChain)
    }

    override suspend fun getFilteredFor(relayChain: String, filter: (LeasePeriod) -> Boolean): List<LeasePeriod> = transaction {
        return@transaction getAllForUnfiltered(relayChain).filter(filter)
    }

    private fun getAllForUnfiltered(relayChain: String): List<LeasePeriod> {
        var relayChainEntity: RelayChainEntity = transaction {
            RelayChainEntity.find { RelayChains.chainName eq relayChain }.singleOrNull()
        } ?: return listOf()

        var result = transaction { LeasePeriodEntity.find { LeasePeriods.relayChain eq relayChainEntity.id } }

        return result.map(LeasePeriodEntity::toLeasePeriod)
    }
}