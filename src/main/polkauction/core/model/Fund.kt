package polkauction.core.model

import polkauction.core.model.entities.LeasePeriod
import polkauction.core.model.entities.Parachain

data class Fund(val paraId: Int, val fundInfo: FundInfo)

data class FundExtended(
    val parachainId: Int,
    val parachainName: String?,
    val website: String?,
    val polkadotJsExplorerUrl: String?,
    val fundInfo: FundInfoExtended
)

fun Fund.extends(parachain: Parachain?, leasePeriods: List<LeasePeriod>) = FundExtended(
    parachainId = paraId,
    parachainName = parachain?.parachainName,
    website = parachain?.website,
    polkadotJsExplorerUrl = parachain?.polkadotJsExplorerUrl,
    fundInfo.extends(leasePeriods)
)
