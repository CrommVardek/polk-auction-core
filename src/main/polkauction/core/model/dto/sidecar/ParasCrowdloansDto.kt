package polkauction.core.model.dto.sidecar

import kotlinx.serialization.Serializable

@Serializable
data class ParasCrowdloansDto(val at: BlockIdentifiersDto, val funds: List<FundDto>)
