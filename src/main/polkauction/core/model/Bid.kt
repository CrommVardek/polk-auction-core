package polkauction.core.model

import polkauction.core.model.entities.Parachain

data class Bid(val accountId: String, val paraId: Number, val amount: Double)

data class BidExtended(val accountId: String, val parachainId: Int, val amount: Double, val parachainName: String?,
                       val website: String?, val polkadotJsExplorerUrl: String?)

fun Bid.extends(parachain : Parachain?) =
    BidExtended(accountId, paraId.toInt(), amount, parachain?.parachainName, parachain?.website,
        parachain?.polkadotJsExplorerUrl)
