package polkauction.core.model.dto.sidecar

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlinx.serialization.Serializable

@Serializable
@JsonIgnoreProperties(ignoreUnknown = true)
data class BlockDto(val number: Number, val extrinsics: List<ExtrinsicDto>)

@Serializable
@JsonIgnoreProperties(ignoreUnknown = true)
data class ExtrinsicDto(val method: MethodDto, val args: ArgsDto?)

@Serializable
@JsonIgnoreProperties(ignoreUnknown = true)
data class MethodDto(val pallet: String)

@Serializable
@JsonIgnoreProperties(ignoreUnknown = true)
data class ArgsDto(val now: String?)

fun BlockDto.getTimeStamp() =
    this.extrinsics.find { e -> e.method.pallet == "timestamp" }?.args?.now?.toLong()