package polkauction.core.model.dto.sidecar

import kotlinx.serialization.Serializable

@Serializable
data class BlockIdentifiersDto(val hash: String, val height: String)
