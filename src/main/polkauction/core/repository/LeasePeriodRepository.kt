package polkauction.core.repository

import org.jetbrains.exposed.sql.transactions.transaction
import polkauction.core.model.entities.*

class LeasePeriodRepository : ILeasePeriodRepository {
    override suspend fun getAllFor(relayChain: String): List<LeasePeriod> = transaction {

        var relayChainEntity: RelayChainEntity = transaction {
            RelayChainEntity.find { RelayChains.chainName eq relayChain }.singleOrNull()
        } ?: return@transaction listOf()

        var result = transaction { LeasePeriodEntity.find { LeasePeriods.relayChain eq relayChainEntity.id } }

        return@transaction result.map(LeasePeriodEntity::toLeasePeriod)
    }
}