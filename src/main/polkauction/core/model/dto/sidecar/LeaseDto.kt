package polkauction.core.model.dto.sidecar

import kotlinx.serialization.Serializable

@Serializable
data class LeaseDto(val leasePeriodIndex: String, val account: String, val deposit: String)
