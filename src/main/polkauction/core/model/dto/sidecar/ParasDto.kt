package polkauction.core.model.dto.sidecar

import kotlinx.serialization.Serializable

@Serializable
data class ParasDto(val at: BlockIdentifiersDto, val paras: List<ParaDto>){

}
