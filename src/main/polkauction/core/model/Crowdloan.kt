package polkauction.core.model

import polkauction.core.model.entities.LeasePeriod
import polkauction.core.model.entities.Parachain

data class Crowdloan(val funds: List<Fund>)

data class CrowdloanExtended(val funds: List<FundExtended>)

fun Crowdloan.extends(parachains: List<Parachain>, leasePeriods: List<LeasePeriod>) = CrowdloanExtended(
    funds.map { it.extends(parachains.singleOrNull { parachain -> parachain.parachainId == it.paraId }, leasePeriods ) }
)