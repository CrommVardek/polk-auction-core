package vardek.polkauction.core.model

enum class ParachainLifeCycle {
    ONBOARDING,
    PARATHREAD,
    PARACHAIN,
    UPGRADING_PARATHREAD,
    DOWNGRADING_PARACHAIN,
    OFFBOARDING_PARATHREAD,
    OFFBOARDING_PARACHAIN
}