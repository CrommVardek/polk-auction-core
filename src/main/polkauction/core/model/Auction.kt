package polkauction.core.model

import polkauction.core.model.entities.Parachain

data class Auction(val beginEnd: String, val finishEnd: String, val phase: AuctionPhase, val auctionIndex: Int,
                   val leasePeriods: List<Int>, val currentWinning: List<WinningInformation>)

data class AuctionExtended(val beginEnd: String, val finishEnd: String, val phase: AuctionPhase, val auctionIndex: Int,
                           val leasePeriods: List<Int>, val currentWinning: List<WinningInformationExtended>)

fun Auction.extends(parachains: List<Parachain>) =
    AuctionExtended(beginEnd, finishEnd, phase, auctionIndex, leasePeriods,
        currentWinning.map{ it.extends(parachains.singleOrNull { parachain -> parachain.parachainId == it.bid?.paraId } ) })