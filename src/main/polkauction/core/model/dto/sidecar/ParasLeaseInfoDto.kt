package polkauction.core.model.dto.sidecar

import kotlinx.serialization.Serializable

@Serializable
data class ParasLeaseInfoDto(val at: BlockIdentifiersDto, val paraLifecycle: String, val onboardingAs: String = "", val leases: List<LeaseDto>? = listOf())