package polkauction.core.service

import polkauction.core.model.Crowdloan
import polkauction.core.model.entities.LeasePeriod
import polkauction.core.model.mapper.toCrowdloan
import polkauction.core.model.with
import polkauction.core.repository.IParachainRepository
import polkauction.core.service.sidecar.ISidecarClientFactory

class CrowdloanService(
    private val parachainRepository: IParachainRepository,
    private val leasePeriodService: ILeasePeriodService,
    private val sidecarClientFactory: ISidecarClientFactory
) : ICrowdloanService {
    override suspend fun getCurrentCrowdloan(chain: String): Crowdloan {
        val relayChainCapitalized = chain.lowercase().capitalize()
        val sidecarClient = sidecarClientFactory.getSidecarClient(chain)
        val crowdloan = sidecarClient.getCrowdloan()
        val parachainsEntities = parachainRepository.getAllFor(relayChainCapitalized)
        val filter: (LeasePeriod) -> Boolean = { lp -> lp.period >= crowdloan.funds.map { f -> f.fundInfo.firstPeriod.toInt() }.minOrNull()!! && lp.period <= crowdloan.funds.map { f -> f.fundInfo.firstPeriod.toInt() }.maxOrNull()!! }
        val leasePeriods = leasePeriodService.getFilteredFor(chain, filter)
        return crowdloan.toCrowdloan().with(parachainsEntities, leasePeriods)
    }
}