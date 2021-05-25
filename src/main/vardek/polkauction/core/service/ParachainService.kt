package vardek.polkauction.core.service

import vardek.polkauction.core.model.Parachain
import vardek.polkauction.core.service.sidecar.ISidecarClient

class ParachainService(private val sidecarClient: ISidecarClient): IParachainService {

    override suspend fun GetAllCurrentParachains(): List<Parachain> {
        return sidecarClient.GetParas()
    }
}