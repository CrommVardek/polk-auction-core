package polkauction.core.model

import kotlinx.collections.immutable.ImmutableList


data class Auction(val beginEnd: String, val finishEnd: String, val phase: AuctionPhase, val auctionIndex: Int,
                   val leasePeriods: ImmutableList<Int>, val currentWinning: ImmutableList<WinningInformation>) {
}