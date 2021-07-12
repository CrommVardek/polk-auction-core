package polkauction.core.model

import kotlinx.collections.immutable.ImmutableSet

data class WinningInformation(val description: String, val bid: Bid? = null, val leases: Set<String>) {

}
