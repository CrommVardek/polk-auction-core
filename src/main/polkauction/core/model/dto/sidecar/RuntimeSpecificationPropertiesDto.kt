package polkauction.core.model.dto.sidecar

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlinx.serialization.Serializable

@Serializable
@JsonIgnoreProperties(ignoreUnknown = true)
data class RuntimeSpecificationPropertiesDto(val tokenDecimals: List<String>, val tokenSymbol: List<String>)
