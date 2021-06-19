package polkauction.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Parachain(val paraId: Number, val parachainLifeCycle: ParachainLifeCycle, val onboardingAs: OnboardingType = OnboardingType.NOT_APPLICABLE,
                     val currentLeases: MutableList<Lease> = mutableListOf()
) {

}

