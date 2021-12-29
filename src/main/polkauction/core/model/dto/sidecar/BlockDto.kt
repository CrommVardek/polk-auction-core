package polkauction.core.model.dto.sidecar

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlinx.serialization.Serializable

@Serializable
@JsonIgnoreProperties(ignoreUnknown = true)
data class BlockDto(val number: Number, val extrinsics: List<ExtrinsicDto>)

@Serializable
@JsonIgnoreProperties(ignoreUnknown = true)
data class ExtrinsicDto(val method: MethodDto)

@Serializable
@JsonIgnoreProperties(ignoreUnknown = true)
data class MethodDto(val pallet: String, val args: ArgsDto?)

@Serializable
@JsonIgnoreProperties(ignoreUnknown = true)
data class ArgsDto(val now: Number?)

fun BlockDto.getTimeStamp() =
    this.extrinsics.find { e -> e.method.pallet == "timestamp" }?.method?.args?.now?.toLong()