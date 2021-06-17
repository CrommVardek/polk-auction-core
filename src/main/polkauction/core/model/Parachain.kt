package polkauction.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Parachain(val paraId: String, val parachainLifeCycle: ParachainLifeCycle, val onboardingAs: OnboardingType = OnboardingType.NOT_APPLICABLE,
                     val leases: Collection<Lease> = listOf()) {

}

