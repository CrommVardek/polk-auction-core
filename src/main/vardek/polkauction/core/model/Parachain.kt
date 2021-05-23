package vardek.polkauction.core.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.serialization.Serializable

@Serializable
data class Parachain(val paraId: String, val parachainLifeCycle: ParachainLifeCycle, val onboardingAs: OnboardingType,
                     val leases: ImmutableList<Lease>) {

}

