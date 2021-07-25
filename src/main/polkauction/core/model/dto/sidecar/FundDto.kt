package polkauction.core.model.dto.sidecar

import kotlinx.serialization.Serializable

@Serializable
data class FundDto(val paraId: String, val fundInfo: FundInfoDto)
