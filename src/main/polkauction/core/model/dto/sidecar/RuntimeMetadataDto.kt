package polkauction.core.model.dto.sidecar

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlinx.serialization.Serializable

@Serializable
@JsonIgnoreProperties(ignoreUnknown = true)
data class RuntimeMetadataDto(val magicNumber: String, val metadata: MetadataDto)

@Serializable
@JsonIgnoreProperties(ignoreUnknown = true)
data class MetadataDto(val v14: VersionDto)

@Serializable
@JsonIgnoreProperties(ignoreUnknown = true)
data class VersionDto(val pallets: List<PalletDto>)

@Serializable
@JsonIgnoreProperties(ignoreUnknown = true)
data class PalletDto(val name: String, val constants: List<ConstantDto>)

@Serializable
@JsonIgnoreProperties(ignoreUnknown = true)
data class ConstantDto(val name: String, val type: String, val value: String)

