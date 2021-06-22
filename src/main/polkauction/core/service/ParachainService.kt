package polkauction.core.service

import polkauction.core.model.Parachain
import polkauction.core.model.mapper.toLease
import polkauction.core.model.mapper.toParachain
import polkauction.core.service.sidecar.ISidecarClient

class ParachainService(private val sidecarClient: ISidecarClient): IParachainService {

    override suspend fun GetAllCurrentParachains(): List<Parachain> {
        val parachains =  sidecarClient.getParas().paras.map { it.toParachain() }

        parachains.forEach { p -> sidecarClient.getParaLeaseInfo(p.paraId).leases?.forEach { p.currentLeases.add(it.toLease()) }   }

        return parachains;
    }
}