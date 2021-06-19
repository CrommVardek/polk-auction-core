package polkauction.core.service.sidecar

import polkauction.core.model.Auction
import polkauction.core.model.dto.sidecar.ParasDto
import polkauction.core.model.dto.sidecar.ParasLeaseInfoDto

interface ISidecarClient {

    suspend fun GetParas(): ParasDto

    suspend fun GetParaLeaseInfo(paraId: Number): ParasLeaseInfoDto

    suspend fun GetAuction(): Auction
}