package polkauction.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Parachain(val paraId: Number, val parachainLifeCycle: ParachainLifeCycle, val onboardingAs: OnboardingType = OnboardingType.NOT_APPLICABLE,
                     val currentLeases: MutableList<Lease> = mutableListOf()
)

@Serializable
data class ParachainExtended(val parachain: Parachain, val parachainName: String?, val website: String?, val polkadotJsExplorerUrl: String?, val relayChainName: String?)

fun Parachain.extends(parachain: polkauction.core.model.entities.Parachain?) =
    ParachainExtended(parachain = this, parachain?.parachainName, parachain?.website, parachain?.polkadotJsExplorerUrl, parachain?.relayChain?.chainName)
