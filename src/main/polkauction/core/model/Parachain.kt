package polkauction.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Parachain(
    val parachainId: Number,
    val parachainLifeCycle: ParachainLifeCycle,
    val onboardingAs: OnboardingType = OnboardingType.NOT_APPLICABLE,
    val currentLeases: MutableList<Lease> = mutableListOf(),
    val parachainName: String? = null,
    val website: String? = null,
    val polkadotJsExplorerUrl: String? = null,
    val relayChainName: String? = null
)

fun Parachain.with(parachain: polkauction.core.model.entities.Parachain?) =
    Parachain(
        parachainId,
        parachainLifeCycle,
        onboardingAs,
        currentLeases,
        parachain?.parachainName,
        parachain?.website,
        parachain?.polkadotJsExplorerUrl,
        parachain?.relayChain?.chainName
    )
