package polkauction.core.model

import polkauction.core.model.entities.LeasePeriod
import polkauction.core.model.entities.Parachain

data class Auction(
    val beginEnd: String,
    val finishEnd: String,
    val phase: AuctionPhase,
    val auctionIndex: Int,
    val leasePeriodIndexes: List<Int>? = null,
    val currentWinning: List<WinningInformation>,
    val leasePeriods: List<LeasePeriod>? = null
)

fun Auction.with(parachains: List<Parachain>, leasePeriods: List<LeasePeriod>) =
    this.copy(
        currentWinning = this.currentWinning.map { it.with(parachains.singleOrNull { parachain -> parachain.parachainId == it.bid?.parachainId }) },
        leasePeriods = leasePeriods.filter { lp -> leasePeriodIndexes?.find { lp.period == it } != null }.sortedBy { it.period })
