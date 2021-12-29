package polkauction.core.model.mapper

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import polkauction.core.model.AuctionPhase
import polkauction.core.model.OnboardingType
import polkauction.core.model.ParachainLifeCycle
import polkauction.core.model.dto.sidecar.*
import kotlin.test.assertNull


class SidecarDtoMapperTest {

    @Test
    fun validParaDtoShouldMapToParachainModel(){
        val paraDto = ParaDto("31216458", "onboarding", "Parachain")

        val parachain = paraDto.toParachain()

        assertEquals(OnboardingType.PARACHAIN, parachain.onboardingAs)
        assertEquals(ParachainLifeCycle.ONBOARDING, parachain.parachainLifeCycle)
        assertEquals(paraDto.paraId.toLong(), parachain.parachainId)
    }

    @Test
    fun invalidParaDtoLifeCycleMapToModelShouldThrowWhenMapToParachain(){
        val paraDto = ParaDto("31216458", "notAValidParaLifeCycle", "Parachain")
        assertThrows(IllegalArgumentException::class.java) { paraDto.toParachain() }
    }

    @Test
    fun invalidParaDtoParaIdMapToModelShouldThrowWhenMapToParachain(){
        val paraDto = ParaDto("notAnId", "onboarding", "Parachain")
        assertThrows(NumberFormatException::class.java) { paraDto.toParachain() }
    }

    @Test
    fun invalidParaDtoOnboardingTypeMapToModelShouldCreateANonApplicableOnboardingType(){
        val paraDto = ParaDto("31216458", "onboarding", "ThatIsNotAValidOnBoarding")

        val parachain = paraDto.toParachain()

        assertEquals(OnboardingType.NOT_APPLICABLE, parachain.onboardingAs)
    }

    @Test
    fun validLeaseDtoShouldMapToLeaseModel(){
        val leaseDto = LeaseDto("1-20", "JCkD7cRTpfkQmk5v6XvWvR1JPTvrouPXSGmQqtWPcJQKFzx", "128.00003232")

        val leaseModel = leaseDto.toLease()

        assertEquals(leaseDto.deposit.toDouble(), leaseModel.deposit)
        assertEquals(leaseDto.account, leaseModel.account)
        assertEquals(leaseDto.leasePeriodIndex, leaseModel.leaseIndexPeriod)
    }

    @Test
    fun invalidLeaseDtoDepositShouldThrowWhenMapToLease(){
        val leaseDto = LeaseDto("1-20", "JCkD7cRTpfkQmk5v6XvWvR1JPTvrouPXSGmQqtWPcJQKFzx", "oops")

        assertThrows(NumberFormatException::class.java) { leaseDto.toLease() }
    }

    @Test
    fun validBidDtoShouldMapToBidModel(){
        val bidDto = BidDto("JCkD7cRTpfkQmk5v6XvWvR1JPTvrouPXSGmQqtWPcJQKFzx", "2099", "123456780987")

        val bidModel = bidDto.toBid()

        assertEquals(bidDto.accountId, bidModel.accountId)
        assertEquals(bidDto.paraId.toInt(), bidModel.parachainId)
        assertEquals(bidDto.amount.toDouble(), bidModel.amount)
    }

    @Test
    fun invalidBidDtoParaIdShouldThrowWhenMapping(){
        val bidDto = BidDto("JCkD7cRTpfkQmk5v6XvWvR1JPTvrouPXSGmQqtWPcJQKFzx", "azerty", "123456780987")

        assertThrows(NumberFormatException::class.java) { bidDto.toBid() }
    }

    @Test
    fun invalidBidDtoAmountShouldThrowWhenMapping(){
        val bidDto = BidDto("JCkD7cRTpfkQmk5v6XvWvR1JPTvrouPXSGmQqtWPcJQKFzx", "2099", "123.notANumber")

        assertThrows(NumberFormatException::class.java) { bidDto.toBid() }
    }

    @Test
    fun validWinningDataDtoShouldMapToWinningInformationModel(){
        val winningDataDto = WinningDataDto("descr.", null, listOf("13", "14", "15", "16", "17", "18", "19", "20"))

        val winningInformationModel = winningDataDto.toWinningInformation()

        assertEquals(winningDataDto.description, winningInformationModel.description)
        winningDataDto.leaseSet.forEach{ assert(winningInformationModel.leases.contains(it)) }
        assertNull(winningInformationModel.bid)
    }

    @Test
    fun validAuctionDtoShouldMapToAuctionModel(){
        val auctionDto = AuctionDto(BlockIdentifiersDto("0x6cdf2b4c9d7aa5e5f0a6217def7ca53b46691300853f40b3be4e5d9df36f0044", "8200000"), "8151516", "8223516", "endPeriod", "3", listOf("13","14","15","16","17","18","19","20"))

        val auctionModel = auctionDto.toAuction()

        assertEquals(auctionDto.beginEnd, auctionModel.beginEnd)
        assertEquals(auctionDto.finishEnd, auctionModel.finishEnd)
        assertEquals(auctionDto.auctionIndex.toInt(), auctionModel.auctionIndex)
        auctionDto.leasePeriods?.forEach {  assert(auctionModel.leasePeriodIndexes!!.contains(it.toInt())) }
        assertEquals(AuctionPhase.END_PERIOD, auctionModel.phase)
    }

    @Test
    fun validAuctionDtoWithNoPhaseShouldMapToAuctionModelWithNoOngoingAuctionPhase(){
        val auctionDto = AuctionDto(BlockIdentifiersDto("0x78e4d78c927191c36113dcb3669b241eecdbd9dc1258802fd7f67a3de3ba4ea4", "8424000"), null, null, null, "5", null, null)

        val auctionModel = auctionDto.toAuction()

        assertEquals("", auctionModel.beginEnd)
        assertEquals("", auctionModel.finishEnd)
        assertEquals(auctionDto.auctionIndex.toInt(), auctionModel.auctionIndex)
        assertEquals(AuctionPhase.NO_ONGOING_AUCTION, auctionModel.phase)
    }

}