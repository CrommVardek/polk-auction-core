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
    beginEnd = beginEnd ?: "",
    finishEnd = finishEnd ?: "",
    phase = valueOrDefault(AuctionPhase.NO_ONGOING_AUCTION) { AuctionPhase.valueOf(phase?.camelToUpperSnakeCase() ?: "") },
    auctionIndex = auctionIndex.toInt(),
    leasePeriods = leasePeriods?.map{it.toInt()} ?: listOf(),
    currentWinning = winning?.map{it.toWinningInformation()} ?: listOf(),
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

fun VerifierDto.toVerifier() = Verifier(
    sr25519 = sr25519
)

fun FundInfoDto.toFundInfo() = FundInfo(
    depositor = depositor,
    verifier = verifier?.toVerifier(),
    deposit = deposit.toDouble(),
    raised = raised.toDouble(),
    end = end.toLong(),
    cap = cap.toDouble(),
    firstPeriod = firstPeriod,
    lastPeriod = lastPeriod,
    trieIndex = trieIndex,
)

fun FundDto.toFund() = Fund(
    paraId = paraId.toInt(),
    fundInfo = fundInfo.toFundInfo()
)

fun ParasCrowdloansDto.toCrowdloan() = Crowdloan(
    funds = funds.map { it.toFund() }
)

fun RuntimeSpecificationPropertiesDto.toRuntimeSpecificationProperties() = RuntimeSpecificationProperties(
    tokenDecimal = tokenDecimals.firstOrNull()?.toInt() ?: 1,
    tokenSymbol = tokenSymbol.firstOrNull() ?: ""
)

fun RuntimeSpecificationDto.toRuntimeSpecification() = RuntimeSpecification(
    name = specName,
    version = specVersion,
    properties = properties.toRuntimeSpecificationProperties()
)