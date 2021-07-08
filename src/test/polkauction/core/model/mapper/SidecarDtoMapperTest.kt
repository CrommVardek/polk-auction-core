package polkauction.core.model.mapper

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import polkauction.core.model.OnboardingType
import polkauction.core.model.ParachainLifeCycle
import polkauction.core.model.dto.sidecar.LeaseDto
import polkauction.core.model.dto.sidecar.ParaDto


class SidecarDtoMapperTest {

    @Test
    fun validParaDtoShouldMapToParachainModel(){
        val paraDto = ParaDto("31216458", "onboarding", "Parachain")

        val parachain = paraDto.toParachain()

        assertEquals(OnboardingType.PARACHAIN, parachain.onboardingAs)
        assertEquals(ParachainLifeCycle.ONBOARDING, parachain.parachainLifeCycle)
        assertEquals(paraDto.paraId.toLong(), parachain.paraId)
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

}