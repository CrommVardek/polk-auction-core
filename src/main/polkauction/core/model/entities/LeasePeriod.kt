package polkauction.core.model.entities

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object LeasePeriods : IntIdTable() {
    val period = integer("Period")
    val relayChain = reference("RelayChain", RelayChains)
    val blockStart = integer("blockStart")
    val blockEnd = integer("blockEnd")

    init {
        index(true, period, relayChain)
    }
}

class LeasePeriodEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<LeasePeriodEntity>(LeasePeriods)

    var period by LeasePeriods.period
    var relayChain by RelayChainEntity referencedOn LeasePeriods.relayChain
    var blockStart by LeasePeriods.blockStart
    var blockEnd by LeasePeriods.blockEnd

    fun toLeasePeriod() = LeasePeriod(period, relayChain.toRelayChain(), blockStart, blockEnd)
}

data class LeasePeriod(
    val period: Int,
    val relayChain: RelayChain,
    val blockNumberStart: Int,
    val blockNumberEnd: Int,
    val startTimeStamp: Long? = null,
    val endTimeStamp: Long? = null,
)

fun acceptLeasePeriodsFrom(leasePeriodIndexes: List<String>): (leasePeriod: LeasePeriod) -> Boolean = {
        leasePeriod -> leasePeriodIndexes.any{ it == leasePeriod.period.toString() }
}
