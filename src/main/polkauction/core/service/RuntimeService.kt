package polkauction.core.service

import polkauction.core.model.RuntimeSpecification
import polkauction.core.model.mapper.toRuntimeSpecification
import polkauction.core.service.sidecar.getSidecarClient

class RuntimeService: IRuntimeService {
    override suspend fun getSpecification(chain: String): RuntimeSpecification {
        val sidecarClient = getSidecarClient(chain);
        return sidecarClient.getRuntimeSpecification().toRuntimeSpecification()
    }
}