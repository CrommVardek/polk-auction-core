package polkauction.core.model

import kotlinx.serialization.Serializable

@Serializable
enum class OnboardingType {
    PARATHREAD,
    PARACHAIN,
    NOT_APPLICABLE
}

fun valueOrDefault(default: OnboardingType, block: () -> OnboardingType) = try { block() } catch (e: Throwable) { default }
