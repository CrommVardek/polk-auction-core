package polkauction.core.repository

import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import polkauction.core.model.entities.*

class ParachainRepository : IParachainRepository {
    override suspend fun getAllFor(relayChain: String): List<Parachain> = transaction {

        var relayChainEntity: RelayChainEntity = transaction {
            RelayChainEntity.find { RelayChains.chainName eq relayChain }.singleOrNull()
        } ?: return@transaction listOf()

        var result = transaction { ParachainEntity.find { Parachains.relayChain eq relayChainEntity.id } }

        return@transaction result.map(ParachainEntity::toParachain)
    }

    override suspend fun getByIdFor(parachainId: Int, relayChain: String): Parachain? = transaction {
        var relayChainEntity: RelayChainEntity = transaction {
            RelayChainEntity.find { RelayChains.chainName eq relayChain }.singleOrNull()
        } ?: return@transaction null

        var result = transaction {
            ParachainEntity.find {
                (Parachains.relayChain eq relayChainEntity.id) and (Parachains.parachainId eq parachainId)
            }
        }.singleOrNull()

        return@transaction result?.toParachain()
    }
}