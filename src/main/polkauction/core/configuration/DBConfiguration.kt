package polkauction.core.configuration

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory
import polkauction.core.model.EcoSystemConstants.KUSAMA_CHAIN_NAME
import polkauction.core.model.EcoSystemConstants.POLKADOT_CHAIN_NAME
import polkauction.core.model.entities.ParachainEntity
import polkauction.core.model.entities.Parachains
import polkauction.core.model.entities.RelayChainEntity
import polkauction.core.model.entities.RelayChains

const val HIKARI_CONFIG_KEY = "ktor.hikariconfig"

fun Application.initDB() {
    val configPath = environment.config.property(HIKARI_CONFIG_KEY).getString()
    val dbConfig = HikariConfig(configPath)
    val dataSource = HikariDataSource(dbConfig)
    Database.connect(dataSource)
    reCreateTables()
    initialLoad()
    LoggerFactory.getLogger(Application::class.simpleName).info("Initialized Database")
}

private fun reCreateTables() = transaction {
    SchemaUtils.drop(
        RelayChains,
        Parachains
    )
    SchemaUtils.create(
        RelayChains,
        Parachains
    )
}

private fun initialLoad() = transaction {
    insertRelayChains()
    insertParachains()
}

private fun insertRelayChains() = transaction {
    RelayChainEntity.new {
        chainName = POLKADOT_CHAIN_NAME
    }
    RelayChainEntity.new {
        chainName = KUSAMA_CHAIN_NAME
    }
}

private fun insertParachains() = transaction {
    val kusamaRelayChain = RelayChainEntity.find { RelayChains.chainName eq KUSAMA_CHAIN_NAME }.single()

    ParachainEntity.new {
        parachainId = 1000
        parachainName = "Statemine"
        relayChain = kusamaRelayChain
        website = "https://polkadot.network/statemine-upgrade-launches-new-phase-of-parachain-functionality/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fkusama-statemine-rpc.paritytech.net#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2000
        parachainName = "Karura"
        relayChain = kusamaRelayChain
        website = "https://acala.network/karura"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fkarura-rpc-1.aca-api.network#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2023
        parachainName = "Moonriver"
        relayChain = kusamaRelayChain
        website = "https://moonbeam.network/networks/moonriver/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fwss.moonriver.moonbeam.network#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2007
        parachainName = "Shiden"
        relayChain = kusamaRelayChain
        website = "https://shiden.astar.network/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fshiden.api.onfinality.io%2Fpublic-ws#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2004
        parachainName = "Khala"
        relayChain = kusamaRelayChain
        website = "https://phala.network/en/khala/"
    }
    ParachainEntity.new {
        parachainId = 2001
        parachainName = "Bifrost"
        relayChain = kusamaRelayChain
        website = "https://bifrost.finance/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fbifrost-rpc.liebi.com%2Fws#/explorer"
    }
}
