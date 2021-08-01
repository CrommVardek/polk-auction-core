package polkauction.core.service

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import polkauction.core.model.Parachain
import polkauction.core.model.mapper.toLease
import polkauction.core.model.mapper.toParachain
import polkauction.core.repository.IParachainRepository
import polkauction.core.service.sidecar.ISidecarClient
import polkauction.core.service.sidecar.getSidecarClient

class ParachainService: IParachainService, KoinComponent {

    val parachainRepository: IParachainRepository by inject()

    override suspend fun getAllCurrentParachains(chain: String): List<Parachain> {
        val sidecarClient = getSidecarClient(chain)
        val parachains =  sidecarClient.getParas().paras.map { it.toParachain() }

        parachains.forEach { loadLeases(sidecarClient, it) }

        return parachains;
    }

    override suspend fun getParachain(chain: String, id: Number): Parachain? {
        val sidecarClient = getSidecarClient(chain)
        val parachains =  sidecarClient.getParas().paras.map { it.toParachain() }

        val parachain = parachains.singleOrNull { it.paraId == id }

        if(parachain == null)
            return parachain

        loadLeases(sidecarClient, parachain)

        return parachain
    }

    private suspend fun loadLeases(sidecarClient: ISidecarClient, parachain: Parachain) {
        sidecarClient.getParaLeaseInfo(parachain.paraId).leases?.forEach { parachain.currentLeases.add(it.toLease()) }
    }
}