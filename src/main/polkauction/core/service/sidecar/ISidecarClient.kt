package polkauction.core.service.sidecar

import polkauction.core.model.dto.sidecar.*

interface ISidecarClient {

    suspend fun getParas(): ParasDto

    suspend fun getParaLeaseInfo(paraId: Number): ParasLeaseInfoDto

    suspend fun getAuction(): AuctionDto

    suspend fun getCrowdloan(): ParasCrowdloansDto

    suspend fun getRuntimeSpecification(): RuntimeSpecificationDto

    suspend fun getMetadata(): RuntimeMetadataDto

    suspend fun getBlockAt(height: Number): BlockDto

    suspend fun getBlockHead(): BlockDto
}