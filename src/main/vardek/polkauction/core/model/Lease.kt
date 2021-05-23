package vardek.polkauction.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Lease(val leaseIndexPeriod: String, val account: String, val deposit: Int)