package polkauction.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Parachain(val paraId: Number, val parachainLifeCycle: ParachainLifeCycle, val onboardingAs: OnboardingType = OnboardingType.NOT_APPLICABLE,
                     val currentLeases: MutableList<Lease> = mutableListOf()
)

class ParachainExtended(parachain: Parachain, parachainName: String?, website: String?, polkadotJsExplorerUrl: String?, relayChainName: String?)

//TODO mapper ?

fun Parachain.extends(parachain: polkauction.core.model.entities.Parachain?) =
    ParachainExtended(parachain = this, parachain?.parachainName, parachain?.website, parachain?.polkadotJsExplorerUrl, parachain?.relayChain?.chainName)
