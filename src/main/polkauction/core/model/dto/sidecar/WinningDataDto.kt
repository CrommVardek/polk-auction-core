package polkauction.core.model.dto.sidecar

import kotlinx.serialization.Serializable

@Serializable
data class WinningDataDto(val description: String = "", val bid: BidDto, val leaseSet: List<String> = listOf())
