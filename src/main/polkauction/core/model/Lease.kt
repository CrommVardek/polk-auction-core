package polkauction.core.model

import kotlinx.serialization.Serializable
import polkauction.core.model.entities.LeasePeriod

@Serializable
data class Lease(
    val leaseIndexPeriod: String,
    val account: String,
    val deposit: Double,
    val startTimeStamp: Long? = null,
    val endTimeStamp: Long? = null
)

fun Lease.with(leasePeriods: List<LeasePeriod>) =
    Lease(
        leaseIndexPeriod,
        account,
        deposit,
        leasePeriods.firstOrNull { it.period.toString() == leaseIndexPeriod }?.startTimeStamp,
        leasePeriods.firstOrNull { it.period.toString() == leaseIndexPeriod }?.endTimeStamp
    )