package polkauction.core.model

import polkauction.core.model.entities.LeasePeriod
import java.time.LocalDateTime

data class FundInfo(
    val depositor: String,
    val verifier: Verifier?,
    val deposit: Double,
    val raised: Double,
    val end: Long,
    val cap: Double,
    val firstPeriod: String,
    val firstPeriodStarts: LocalDateTime? = null,
    val lastPeriod: String,
    val lastPeriodStarts: LocalDateTime? = null,
    val trieIndex: String,
    val state: FundState
)

fun FundInfo.with(leasePeriods: List<LeasePeriod>) = FundInfo(
    depositor,
    verifier,
    deposit,
    raised,
    end,
    cap,
    firstPeriod,
    firstPeriodStarts = leasePeriods.find { firstPeriod == it.period.toString() }?.estimatedDateTimeBegin,
    lastPeriod,
    lastPeriodStarts = leasePeriods.find { firstPeriod == it.period.toString() }?.estimatedDateTimeBegin,
    trieIndex,
    state
)
