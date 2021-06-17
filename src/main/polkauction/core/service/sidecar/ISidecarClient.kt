package polkauction.core.service.sidecar

import polkauction.core.model.Auction
import polkauction.core.model.dto.sidecar.ParasDto

interface ISidecarClient {

    suspend fun GetParas(): ParasDto

    suspend fun GetAuction(): Auction
}