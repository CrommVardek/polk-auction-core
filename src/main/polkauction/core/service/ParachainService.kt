package polkauction.core.service

import polkauction.core.model.Parachain
import polkauction.core.model.mapper.toParachain
import polkauction.core.service.sidecar.ISidecarClient

class ParachainService(private val sidecarClient: ISidecarClient): IParachainService {

    override suspend fun GetAllCurrentParachains(): List<Parachain> {
        return sidecarClient.GetParas().paras.map { it.toParachain() }
    }
}