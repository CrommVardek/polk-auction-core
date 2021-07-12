package polkauction.core.model.mapper

import polkauction.core.model.*
import polkauction.core.model.dto.sidecar.*
import polkauction.core.utils.camelToUpperSnakeCase

fun ParaDto.toParachain() = Parachain(
    paraId = paraId.toLong(),
    parachainLifeCycle =  ParachainLifeCycle.valueOf(paraLifecycle.camelToUpperSnakeCase()),
    onboardingAs = valueOrDefault(OnboardingType.NOT_APPLICABLE) { OnboardingType.valueOf(onboardingAs.toUpperCase()) }
)

fun LeaseDto.toLease() = Lease(
    leaseIndexPeriod = leasePeriodIndex,
    account = account,
    deposit = deposit.toDouble(),
)

fun AuctionDto.toAuction() = Auction(
    beginEnd = beginEnd,
    finishEnd = finishEnd,
    phase = valueOrDefault(AuctionPhase.NO_ONGOING_AUCTION) { AuctionPhase.valueOf(phase.camelToUpperSnakeCase()) },
    auctionIndex = auctionIndex.toInt(),
    leasePeriods = leasePeriods.map{it.toInt()},
    currentWinning = winning.map{it.toWinningInformation()},
)

fun WinningDataDto.toWinningInformation() = WinningInformation(
    description = description,
    bid = bid?.toBid(),
    leases = leaseSet.toSet(),
)

fun BidDto.toBid() = Bid(
    accountId = accountId,
    paraId = paraId.toLong(),
    amount = amount.toDouble(),
)