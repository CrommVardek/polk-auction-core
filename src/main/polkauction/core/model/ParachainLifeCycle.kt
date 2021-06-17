package polkauction.core.model

import kotlinx.serialization.Serializable

@Serializable
enum class ParachainLifeCycle {
    ONBOARDING,
    PARATHREAD,
    PARACHAIN,
    UPGRADING_PARATHREAD,
    DOWNGRADING_PARACHAIN,
    OFFBOARDING_PARATHREAD,
    OFFBOARDING_PARACHAIN
}