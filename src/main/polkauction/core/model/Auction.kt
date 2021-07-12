package polkauction.core.model

data class Auction(val beginEnd: String, val finishEnd: String, val phase: AuctionPhase, val auctionIndex: Int,
                   val leasePeriods: List<Int>, val currentWinning: List<WinningInformation>) {
}