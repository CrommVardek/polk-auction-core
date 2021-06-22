package polkauction.core.model.mapper

import polkauction.core.model.*
import polkauction.core.model.dto.sidecar.LeaseDto
import polkauction.core.model.dto.sidecar.ParaDto
import polkauction.core.utils.camelToUpperSnakeCase

fun ParaDto.toParachain() = Parachain(
    paraId = "$paraId".toLong(),
    parachainLifeCycle =  ParachainLifeCycle.valueOf(paraLifecycle.camelToUpperSnakeCase()),
    onboardingAs = valueOrDefault(OnboardingType.NOT_APPLICABLE) { OnboardingType.valueOf(onboardingAs.toUpperCase()) }
)

fun LeaseDto.toLease() = Lease(
    leaseIndexPeriod = leasePeriodIndex,
    account = account,
    deposit = deposit.toDouble()
)