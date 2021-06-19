package polkauction.core.model.mapper

import polkauction.core.model.Lease
import polkauction.core.model.dto.sidecar.LeaseDto

fun LeaseDto.toLease() = Lease(
    leaseIndexPeriod = leasePeriodIndex,
    account = account,
    deposit = deposit.toDouble()
)