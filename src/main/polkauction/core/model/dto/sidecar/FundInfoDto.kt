package polkauction.core.model.dto.sidecar

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlinx.serialization.Serializable

@Serializable
@JsonIgnoreProperties(ignoreUnknown = true)
data class FundInfoDto(val depositor: String, val verifier: VerifierDto?, val deposit: String, val raised: String,
                       val end: String, val cap: String, val firstPeriod: String, val lastPeriod: String, val trieIndex: String?)
