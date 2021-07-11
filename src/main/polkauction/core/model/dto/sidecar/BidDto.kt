package polkauction.core.model.dto.sidecar

import kotlinx.serialization.Serializable

@Serializable
data class BidDto(val accountId: String, val paraId: String, val amount: String)
