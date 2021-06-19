package polkauction.core.service

import polkauction.core.model.Parachain
import polkauction.core.model.mapper.toLease
import polkauction.core.model.mapper.toParachain
import polkauction.core.service.sidecar.ISidecarClient

class ParachainService(private val sidecarClient: ISidecarClient): IParachainService {

    override suspend fun GetAllCurrentParachains(): List<Parachain> {
        val parachains =  sidecarClient.GetParas().paras.map { it.toParachain() }

        parachains.forEach { p -> sidecarClient.GetParaLeaseInfo(p.paraId).leases?.forEach { p.currentLeases.add(it.toLease()) }   }

        return parachains;
    }
}