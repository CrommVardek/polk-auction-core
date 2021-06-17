package polkauction.core.model.dto.sidecar

import kotlinx.serialization.Serializable

@Serializable
data class ParaDto(val paraId: String, val paraLifecycle: String, val onboardingAs: String = "") {

}
