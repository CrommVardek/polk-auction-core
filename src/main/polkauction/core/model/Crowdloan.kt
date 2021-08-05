package polkauction.core.model

import polkauction.core.model.entities.Parachain

data class Crowdloan(val funds: List<Fund>)

data class CrowdloanExtended(val funds: List<FundExtended>)

fun Crowdloan.extends(parachains: List<Parachain>) = CrowdloanExtended(
    funds.map { it.extends(parachains.single { parachain -> parachain.parachainId == it.paraId } ) }
)