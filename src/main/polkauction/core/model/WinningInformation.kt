package polkauction.core.model

data class WinningInformation(val description: String, val bid: Bid? = null, val leases: Set<String>) {

}
