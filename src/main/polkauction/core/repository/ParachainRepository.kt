package polkauction.core.repository

import org.jetbrains.exposed.sql.transactions.transaction
import polkauction.core.model.entities.*

class ParachainRepository : IParachainRepository {
    override suspend fun getAllFor(relayChain: String): List<Parachain> {

        var relayChainEntity: RelayChainEntity = transaction {
            RelayChainEntity.find { RelayChains.chainName eq relayChain }.singleOrNull()
        } ?: return listOf()

        var result = ParachainEntity.find { Parachains.relayChain eq relayChainEntity.id }

        return result.map(ParachainEntity::toParachain)
    }
}