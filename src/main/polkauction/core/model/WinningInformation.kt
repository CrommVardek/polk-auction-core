package polkauction.core.model

import polkauction.core.model.entities.Parachain

data class WinningInformation(val description: String, val bid: Bid? = null, val leases: Set<String>)

fun WinningInformation.with(parachain: Parachain?) =
    WinningInformation(description, bid?.with(parachain), leases)