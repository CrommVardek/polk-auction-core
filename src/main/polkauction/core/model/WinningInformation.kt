package polkauction.core.model

import polkauction.core.model.entities.Parachain

data class WinningInformation(val description: String, val bid: Bid? = null, val leases: Set<String>)

data class WinningInformationExtended(val description: String, val bid: BidExtended? = null, val leases: Set<String>)

fun WinningInformation.extends(parachain: Parachain?) =
    WinningInformationExtended(description, bid?.extends(parachain), leases)