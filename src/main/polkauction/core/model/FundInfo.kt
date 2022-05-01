package polkauction.core.model

import polkauction.core.model.entities.LeasePeriod

data class FundInfo(
    val depositor: String,
    val verifier: Verifier?,
    val deposit: Double,
    val raised: Double,
    val end: Long,
    val cap: Double,
    val firstPeriod: String,
    val firstPeriodStartTimeStamp: Long? = null,
    val lastPeriod: String,
    val lastPeriodEndTimeStamp: Long? = null,
    val trieIndex: String?,
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
    firstPeriodStartTimeStamp = leasePeriods.find { firstPeriod == it.period.toString() }?.startTimeStamp,
    lastPeriod,
    lastPeriodEndTimeStamp = leasePeriods.find { firstPeriod == it.period.toString() }?.endTimeStamp,
    trieIndex,
    state
)
