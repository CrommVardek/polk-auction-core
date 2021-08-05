package polkauction.core.model

import polkauction.core.model.entities.Parachain

data class Fund(val paraId: Int, val fundInfo: FundInfo)

data class FundExtended(
    val parachainId: Int,
    val parachainName: String?,
    val website: String?,
    val polkadotJsExplorerUrl: String?,
    val fundInfo: FundInfo
)

fun Fund.extends(parachain: Parachain?) = FundExtended(
    parachainId = paraId,
    parachainName = parachain?.parachainName,
    website = parachain?.website,
    polkadotJsExplorerUrl = parachain?.polkadotJsExplorerUrl,
    fundInfo
)
