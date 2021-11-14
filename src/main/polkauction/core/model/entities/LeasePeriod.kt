package polkauction.core.model.entities

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object LeasePeriods : IntIdTable() {
    val period = integer("Period")
    val relayChain = reference("RelayChain", RelayChains)
    val blockStart = integer("blockStart")
    val estimatedDateTimeBegin = datetime("estimatedDateTimeBegin")
    val estimatedDateTimeEnd = datetime("estimatedDateTimeEnd")

    init {
        index(true, period, relayChain)
    }
}
class LeasePeriodEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<LeasePeriodEntity>(LeasePeriods)

    var period by LeasePeriods.period
    var relayChain by RelayChainEntity referencedOn LeasePeriods.relayChain
    var blockStart by LeasePeriods.blockStart
    var estimatedDateTimeBegin by LeasePeriods.estimatedDateTimeBegin
    var estimatedDateTimeEnd by LeasePeriods.estimatedDateTimeEnd

    fun toLeasePeriod() = LeasePeriod(period, relayChain.toRelayChain(), blockStart, estimatedDateTimeBegin, estimatedDateTimeEnd)
}

data class LeasePeriod(
    val period: Int,
    val relayChain: RelayChain,
    val blockStart: Int,
    val estimatedDateTimeBegin: LocalDateTime,
    val estimatedDateTimeEnd: LocalDateTime,
)
