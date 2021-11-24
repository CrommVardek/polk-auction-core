package polkauction.core.model

import polkauction.core.model.entities.LeasePeriod
import polkauction.core.model.entities.Parachain

data class Crowdloan(val funds: List<Fund>)

fun Crowdloan.with(parachains: List<Parachain>, leasePeriods: List<LeasePeriod>) = Crowdloan(
    funds.map { it.with(parachains.singleOrNull { parachain -> parachain.parachainId == it.parachainId }, leasePeriods ) }
)