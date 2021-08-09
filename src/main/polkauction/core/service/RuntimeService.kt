package polkauction.core.service

import polkauction.core.model.RuntimeSpecification
import polkauction.core.model.mapper.toRuntimeSpecification
import polkauction.core.service.sidecar.ISidecarClientFactory

class RuntimeService(private val sidecarClientFactory: ISidecarClientFactory) : IRuntimeService {
    override suspend fun getSpecification(chain: String): RuntimeSpecification {
        val sidecarClient = sidecarClientFactory.getSidecarClient(chain);
        return sidecarClient.getRuntimeSpecification().toRuntimeSpecification()
    }
}