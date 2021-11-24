package polkauction.core.model

import polkauction.core.model.entities.Parachain

data class Bid(
    val accountId: String, val parachainId: Int, val amount: Double, val parachainName: String? = null,
    val website: String? = null, val polkadotJsExplorerUrl: String? = null
)

fun Bid.with(parachain: Parachain?) =
    Bid(
        accountId, parachainId, amount, parachain?.parachainName, parachain?.website,
        parachain?.polkadotJsExplorerUrl
    )
