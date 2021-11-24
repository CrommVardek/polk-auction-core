package polkauction.core.model

import polkauction.core.model.entities.Parachain

data class Auction(val beginEnd: String, val finishEnd: String, val phase: AuctionPhase, val auctionIndex: Int,
                   val leasePeriods: List<Int>, val currentWinning: List<WinningInformation>)

fun Auction.with(parachains: List<Parachain>) =
    Auction(beginEnd, finishEnd, phase, auctionIndex, leasePeriods,
        currentWinning.map{ it.with(parachains.singleOrNull { parachain -> parachain.parachainId == it.bid?.parachainId } ) })