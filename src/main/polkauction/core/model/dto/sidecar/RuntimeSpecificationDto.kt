package polkauction.core.model.dto.sidecar
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlinx.serialization.Serializable

@Serializable
@JsonIgnoreProperties(ignoreUnknown = true)
data class RuntimeSpecificationDto(val at: BlockIdentifiersDto, val authoringVersion: String, val transactionVersion: String,
                                   val implVersion: String, val specName: String, val specVersion: String,
                                   val properties: RuntimeSpecificationPropertiesDto)