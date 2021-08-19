package polkauction.core.service

import polkauction.core.exception.SidecarGetException
import polkauction.core.model.Parachain
import polkauction.core.model.ParachainExtended
import polkauction.core.model.extends
import polkauction.core.model.mapper.toLease
import polkauction.core.model.mapper.toParachain
import polkauction.core.repository.IParachainRepository
import polkauction.core.service.sidecar.ISidecarClient
import polkauction.core.service.sidecar.ISidecarClientFactory

class ParachainService(
    private val parachainRepository: IParachainRepository,
    private val sidecarClientFactory: ISidecarClientFactory
) : IParachainService {

    override suspend fun getAllCurrentParachains(chain: String): List<ParachainExtended> {
        val registeredParachains = parachainRepository.getAllFor(chain.toLowerCase().capitalize())
        val sidecarClient = sidecarClientFactory.getSidecarClient(chain)

        return try {
            val parachains = sidecarClient.getParas().paras.map { it.toParachain() }

            parachains.forEach { loadLeases(sidecarClient, it) }

            parachains.map { it.extends(registeredParachains.find { rp -> rp.parachainId == it.paraId.toInt() }) };
        } catch (e: SidecarGetException) {
            listOf()
        }
    }

    override suspend fun getParachain(chain: String, id: Int): ParachainExtended? {
        val registeredParachain = parachainRepository.getByIdFor(id, chain.toLowerCase().capitalize())
        val sidecarClient = sidecarClientFactory.getSidecarClient(chain.toLowerCase().capitalize())
        val parachains = sidecarClient.getParas().paras.map { it.toParachain() }

        return try {
            val parachain = parachains.singleOrNull { it.paraId.toInt() == id }

            if (parachain == null)
                return parachain

            loadLeases(sidecarClient, parachain)

            parachain.extends(registeredParachain);
        } catch (e: SidecarGetException) {
            null
        }

    }

    private suspend fun loadLeases(sidecarClient: ISidecarClient, parachain: Parachain) {
        sidecarClient.getParaLeaseInfo(parachain.paraId).leases?.forEach { parachain.currentLeases.add(it.toLease()) }
    }
}