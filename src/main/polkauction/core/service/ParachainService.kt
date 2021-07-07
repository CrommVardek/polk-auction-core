package polkauction.core.service

import polkauction.core.model.Parachain
import polkauction.core.model.mapper.toLease
import polkauction.core.model.mapper.toParachain
import polkauction.core.service.sidecar.ISidecarClient

class ParachainService(private val sidecarClient: ISidecarClient): IParachainService {

    override suspend fun getAllCurrentParachains(): List<Parachain> {
        val parachains =  sidecarClient.getParas().paras.map { it.toParachain() }

        parachains.forEach { loadLeases(it) }

        return parachains;
    }

    override suspend fun getParachain(id: Number): Parachain? {
        val parachains =  sidecarClient.getParas().paras.map { it.toParachain() }

        val parachain = parachains.singleOrNull { it.paraId == id }

        if(parachain == null)
            return parachain

        loadLeases(parachain)

        return parachain
    }

    private suspend fun loadLeases(parachain: Parachain) {
        sidecarClient.getParaLeaseInfo(parachain.paraId).leases?.forEach { parachain.currentLeases.add(it.toLease()) }
    }
}