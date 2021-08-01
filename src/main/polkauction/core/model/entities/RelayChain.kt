package polkauction.core.model.entities

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object RelayChains : IntIdTable() {
    val chainName = varchar("ChainName", 255).uniqueIndex()
}

class RelayChainEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<RelayChainEntity>(RelayChains)

    var chainName by RelayChains.chainName

    fun toRelayChain() = RelayChain(id.value, chainName)
}

data class RelayChain(
    val id: Int,
    val chainName: String,
)