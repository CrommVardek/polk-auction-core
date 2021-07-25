package polkauction.core.model.dto.sidecar

import kotlinx.serialization.Serializable

@Serializable
data class AuctionDto(val at: BlockIdentifiersDto, val beginEnd: String? = "", val finishEnd: String? = "",
                      val phase: String? = "", val auctionIndex: String, val leasePeriods: List<String>? = listOf(),
                      val winning: List<WinningDataDto>? = listOf())