package polkauction.core.configuration

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory
import polkauction.core.model.EcoSystemConstants.KUSAMA_CHAIN_NAME
import polkauction.core.model.EcoSystemConstants.KUSAMA_LEASE_PERIOD_DURATION_DAYS
import polkauction.core.model.EcoSystemConstants.POLKADOT_CHAIN_NAME
import polkauction.core.model.entities.*
import java.time.LocalDateTime

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
        Parachains,
        LeasePeriods
    )
    SchemaUtils.create(
        RelayChains,
        Parachains,
        LeasePeriods
    )
}

private fun initialLoad() = transaction {
    insertRelayChains()
    insertParachains()
    insertLeasePeriods()
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
        parachainId = 1001
        parachainName = "Encointer"
        relayChain = kusamaRelayChain
        website = "https://encointer.org/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fapi.kusama.encointer.org#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2000
        parachainName = "Karura"
        relayChain = kusamaRelayChain
        website = "https://acala.network/karura"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fkarura-rpc-1.aca-api.network#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2001
        parachainName = "Bifrost"
        relayChain = kusamaRelayChain
        website = "https://bifrost.finance/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fbifrost-rpc.liebi.com%2Fws#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2004
        parachainName = "Khala"
        relayChain = kusamaRelayChain
        website = "https://phala.network/en/khala/"
    }
    ParachainEntity.new {
        parachainId = 2007
        parachainName = "Shiden"
        relayChain = kusamaRelayChain
        website = "https://shiden.astar.network/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fshiden.api.onfinality.io%2Fpublic-ws#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2008
        parachainName = "Mars"
        relayChain = kusamaRelayChain
        website = "https://aresprotocol.io/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fwss.mars.aresprotocol.io#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2009
        parachainName = "Polkasmith"
        relayChain = kusamaRelayChain
        website = "https://polkasmith.polkafoundry.com/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fwss-polkasmith.polkafoundry.com#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2012
        parachainName = "Crust Shadow"
        relayChain = kusamaRelayChain
        website = "https://crust.network/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fshadow.crust.network%2F#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2013
        parachainName = "SherpaX"
        relayChain = kusamaRelayChain
        website = "https://www.chainx.org/en/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fsherpax.chainx.org#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2015
        parachainName = "Integritee"
        relayChain = kusamaRelayChain
        website = "https://integritee.network/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fkusama.api.integritee.network#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2016
        parachainName = "Sakura"
        relayChain = kusamaRelayChain
        website = "https://clover.finance/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fapi-sakura.clover.finance#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2018
        parachainName = "SubGame Gamma"
        relayChain = kusamaRelayChain
        website = "https://www.subgame.org/#/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fgamma.subgame.org%2F#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2019
        parachainName = "Kpron"
        relayChain = kusamaRelayChain
        website = "https://apron.network/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fkusama-kpron-rpc.apron.network%2F#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2023
        parachainName = "Moonriver"
        relayChain = kusamaRelayChain
        website = "https://moonbeam.network/networks/moonriver/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fwss.moonriver.moonbeam.network#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2077
        parachainName = "Robonomics"
        relayChain = kusamaRelayChain
        website = "https://robonomics.network/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fkusama.rpc.robonomics.network%2F#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2080
        parachainName = "Loom Network"
        relayChain = kusamaRelayChain
        website = "https://loomx.io/"
        polkadotJsExplorerUrl = ""
    }
    ParachainEntity.new {
        parachainId = 2084
        parachainName = "Calamari"
        relayChain = kusamaRelayChain
        website = "https://www.calamari.network/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fsmoothie.calamari.systems%2F#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2085
        parachainName = "Parallel Heiko"
        relayChain = kusamaRelayChain
        website = "https://parallel.fi/networks/kusama.html"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fheiko-rpc.parallel.fi#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2086
        parachainName = "KILT Protocol"
        relayChain = kusamaRelayChain
        website = "https://www.kilt.io/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fspiritnet.api.onfinality.io%2Fpublic-ws#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2087
        parachainName = "Picasso"
        relayChain = kusamaRelayChain
        website = "https://picasso.composable.finance/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fpicasso-rpc.composable.finance#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2088
        parachainName = "Altair"
        relayChain = kusamaRelayChain
        website = "https://centrifuge.io/altair/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Ffullnode.altair.centrifuge.io#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2089
        parachainName = "Genshiro"
        relayChain = kusamaRelayChain
        website = "https://genshiro.equilibrium.io/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fgens-mainnet.equilibrium.io#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2090
        parachainName = "Basilisk"
        relayChain = kusamaRelayChain
        website = "https://bsx.fi/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fbasilisk.api.onfinality.io%2Fpublic-ws#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2092
        parachainName = "Kintsugi"
        relayChain = kusamaRelayChain
        website = "https://kintsugi.interlay.io/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fapi-kin.interlay.io%2Fparachain#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2094
        parachainName = "Unorthodox"
        relayChain = kusamaRelayChain
        website = "https://standard.tech/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Frpc.kusama.standard.tech#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2095
        parachainName = "Quartz"
        relayChain = kusamaRelayChain
        website = "https://unique.network/quartz/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fus-ws-quartz.unique.network#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2096
        parachainName = "Bit.Country Pioneer"
        relayChain = kusamaRelayChain
        website = "https://bit.country/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fpioneer.api.onfinality.io%2Fpublic-ws#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2100
        parachainName = "Subsocial"
        relayChain = kusamaRelayChain
        website = "https://app.subsocial.network/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fpara.subsocial.network"
    }
    ParachainEntity.new {
        parachainId = 2101
        parachainName = "Zeitgeist"
        relayChain = kusamaRelayChain
        website = "https://zeitgeist.pm/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Frpc-0.zeitgeist.pm#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2102
        parachainName = "Pichiu"
        relayChain = kusamaRelayChain
        website = "https://kylin.network/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fkusama.kylin-node.co.uk#/explorer"
    }

    val polkadotRelayChain = RelayChainEntity.find { RelayChains.chainName eq POLKADOT_CHAIN_NAME }.single()

    ParachainEntity.new {
        parachainId = 1000
        parachainName = "Statemint"
        relayChain = polkadotRelayChain
        website = "https://wiki.polkadot.network/docs/build-integrate-assets"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fstatemint-rpc.polkadot.io"
    }
    ParachainEntity.new {
        parachainId = 2000
        parachainName = "Acala"
        relayChain = polkadotRelayChain
        website = "https://acala.network/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Facala-rpc-0.aca-api.network#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2002
        parachainName = "Clover"
        relayChain = polkadotRelayChain
        website = "https://clover.finance/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fclover.api.onfinality.io%2Fpublic-ws#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2003
        parachainName = "Darwinia"
        relayChain = polkadotRelayChain
        website = "https://darwinia.network/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fparachain-rpc.darwinia.network#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2004
        parachainName = "Moonbeam"
        relayChain = polkadotRelayChain
        website = "https://moonbeam.network/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fmoonbeam.api.onfinality.io%2Fpublic-ws#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2006
        parachainName = "Astar"
        relayChain = polkadotRelayChain
        website = "https://astar.network/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Frpc.astar.network#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2008
        parachainName = "Crust"
        relayChain = polkadotRelayChain
        website = "https://crust.network/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Frpc.crust.network#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2011
        parachainName = "Equilibrium"
        relayChain = polkadotRelayChain
        website = "https://equilibrium.io/en"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fnode.equilibrium.io#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2012
        parachainName = "Parallel Finance"
        relayChain = polkadotRelayChain
        website = "https://parallel.fi/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fparallel.api.onfinality.io%2Fpublic-ws#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2013
        parachainName = "Litentry"
        relayChain = polkadotRelayChain
        website = "https://www.litentry.com/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fparachain.litentry.io#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2015
        parachainName = "Manta"
        relayChain = polkadotRelayChain
        website = "https://manta.network/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fpectinata.manta.systems#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2017
        parachainName = "SubGame"
        relayChain = polkadotRelayChain
        website = "https://www.subgame.org/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fgamma.subgame.org%2F#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2018
        parachainName = "SubDAO"
        relayChain = polkadotRelayChain
        website = "https://www.subdao.network/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fparachain-rpc.subdao.org#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2019
        parachainName = "Composable Finance"
        relayChain = polkadotRelayChain
        website = "https://www.composable.finance/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Frpc.composable.finance#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2021
        parachainName = "Efinity"
        relayChain = polkadotRelayChain
        website = "https://efinity.io/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Frpc.efinity.io#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2026
        parachainName = "Nodle"
        relayChain = polkadotRelayChain
        website = "https://www.nodle.com/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Frpc.nodle.com#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2027
        parachainName = "Coinversation"
        relayChain = polkadotRelayChain
        website = "https://www.coinversation.io/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Frpc.coinversation.io%2F#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2028
        parachainName = "Ares Odyssey"
        relayChain = polkadotRelayChain
        website = "https://www.aresprotocol.io/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fwss.odyssey.aresprotocol.io#/"
    }
    ParachainEntity.new {
        parachainId = 2031
        parachainName = "Centrifuge"
        relayChain = polkadotRelayChain
        website = "https://centrifuge.io/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Ffullnode.parachain.centrifuge.io#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2032
        parachainName = "Interlay"
        relayChain = polkadotRelayChain
        website = "https://interlay.io/"
        polkadotJsExplorerUrl = "https://cloudflare-ipfs.com/ipns/dotapps.io/?rpc=wss%3A%2F%2Fapi.interlay.io%2Fparachain#/explorer"
    }
    ParachainEntity.new {
        parachainId = 2034
        parachainName = ""
        relayChain = polkadotRelayChain
        website = ""
        polkadotJsExplorerUrl = ""
    }
    ParachainEntity.new {
        parachainId = 2035
        parachainName = "Phala"
        relayChain = polkadotRelayChain
        website = "https://phala.network/en/"
        polkadotJsExplorerUrl = ""
    }

}

private fun insertLeasePeriods() {
    val kusamaRelayChain = RelayChainEntity.find { RelayChains.chainName eq KUSAMA_CHAIN_NAME }.single()
    LeasePeriodEntity.new {
        period = 17
        relayChain = kusamaRelayChain
        blockStart = 9806873
        estimatedDateTimeBegin = LocalDateTime.of(2021,10,25,9,32,54)
        estimatedDateTimeEnd = getKusamaEstimatedDateTimeEnd()
    }
    LeasePeriodEntity.new {
        period = 18
        relayChain = kusamaRelayChain
        blockStart = 10282200
        estimatedDateTimeBegin = getEstimatedDateTimeBeginFromPreviousPeriod()
        estimatedDateTimeEnd = getKusamaEstimatedDateTimeEnd()
    }
    LeasePeriodEntity.new {
        period = 19
        relayChain = kusamaRelayChain
        blockStart = 10887000
        estimatedDateTimeBegin = getEstimatedDateTimeBeginFromPreviousPeriod()
        estimatedDateTimeEnd = getKusamaEstimatedDateTimeEnd()
    }
    LeasePeriodEntity.new {
        period = 20
        relayChain = kusamaRelayChain
        blockStart = 11491800
        estimatedDateTimeBegin = getEstimatedDateTimeBeginFromPreviousPeriod()
        estimatedDateTimeEnd = getKusamaEstimatedDateTimeEnd()
    }
    LeasePeriodEntity.new {
        period = 21
        relayChain = kusamaRelayChain
        blockStart = 12096600
        estimatedDateTimeBegin = getEstimatedDateTimeBeginFromPreviousPeriod()
        estimatedDateTimeEnd = getKusamaEstimatedDateTimeEnd()
    }
}

private fun LeasePeriodEntity.getKusamaEstimatedDateTimeEnd() =
    estimatedDateTimeBegin.plusDays(KUSAMA_LEASE_PERIOD_DURATION_DAYS)

private fun LeasePeriodEntity.getEstimatedDateTimeBeginFromPreviousPeriod() =
    LeasePeriodEntity.find { LeasePeriods.period eq period - 1 }.single().estimatedDateTimeEnd.plusSeconds(6)
