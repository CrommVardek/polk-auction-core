package polkauction.core.model.entities

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Parachains : IntIdTable() {
    val parachainName = varchar("ChainName", 255)
    val relayChain = reference("relayChain", RelayChains)
}

class ParachainEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<RelayChainEntity>(RelayChains)

    var parachainName by Parachains.parachainName
    var relayChain by RelayChainEntity referencedOn Parachains.relayChain

    fun toParachain() = Parachain(id.value, parachainName, relayChain.toRelayChain())
}

data class Parachain(
    val id: Int,
    val parachainName: String,
    val relayChain: RelayChain
)