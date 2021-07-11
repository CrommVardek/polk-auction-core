package polkauction.core.model

enum class AuctionPhase {
    START_PERIOD,
    END_PERIOD,
    VRF_DELAY,
    NO_ONGOING_AUCTION
}

fun valueOrDefault(default: AuctionPhase, block: () -> AuctionPhase) = try { block() } catch (e: Throwable) { default }
