package polkauction.core.model.mapper

import polkauction.core.model.OnboardingType
import polkauction.core.model.Parachain
import polkauction.core.model.ParachainLifeCycle
import polkauction.core.model.dto.sidecar.ParaDto
import polkauction.core.model.valueOrDefault
import polkauction.core.utils.camelToUpperSnakeCase

fun ParaDto.toParachain() = Parachain(
    paraId = "$paraId".toLong(),
    parachainLifeCycle =  ParachainLifeCycle.valueOf(paraLifecycle.camelToUpperSnakeCase()),
    onboardingAs = valueOrDefault(OnboardingType.NOT_APPLICABLE) { OnboardingType.valueOf(onboardingAs.toUpperCase()) }
)