package polkauction.core.model

import kotlinx.serialization.Serializable
import polkauction.core.model.entities.LeasePeriod

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

fun Parachain.with(parachain: polkauction.core.model.entities.Parachain?, leasePeriods: List<LeasePeriod>) =
    Parachain(
        parachainId,
        parachainLifeCycle,
        onboardingAs,
        currentLeases.map { it.with(leasePeriods) }.toMutableList(),
        parachain?.parachainName,
        parachain?.website,
        parachain?.polkadotJsExplorerUrl,
        parachain?.relayChain?.chainName
    )
