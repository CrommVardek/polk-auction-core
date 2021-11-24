package polkauction.core.model

import polkauction.core.model.entities.LeasePeriod
import polkauction.core.model.entities.Parachain

data class Fund(
    val parachainId: Int,
    val parachainName: String? = null,
    val website: String? = null,
    val polkadotJsExplorerUrl: String? = null,
    val fundInfo: FundInfo
)

fun Fund.with(parachain: Parachain?, leasePeriods: List<LeasePeriod>) = Fund(
    parachainId = parachainId,
    parachainName = parachain?.parachainName,
    website = parachain?.website,
    polkadotJsExplorerUrl = parachain?.polkadotJsExplorerUrl,
    fundInfo.with(leasePeriods)
)
