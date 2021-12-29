package polkauction.core.model

// TODO : replace all of this with On chain information
object EcoSystemConstants {
    const val KUSAMA_CHAIN_NAME = "Kusama"
    const val POLKADOT_CHAIN_NAME = "Polkadot"

    const val KUSAMA_LEASE_PERIOD = 604800
    const val KUSAMA_LEASE_OFFSET = 0

    const val POLKADOT_LEASE_PERIOD = 1209600
    const val POLKADOT_LEASE_OFFSET = 921600

    const val EXPECTED_BLOCK_TIME_MS = 6000
}