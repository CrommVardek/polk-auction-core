package polkauction.core.service.sidecar

import com.fasterxml.jackson.datatype.guava.GuavaModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import polkauction.core.exception.NoSuchChainException
import polkauction.core.model.Auction
import polkauction.core.model.dto.sidecar.ParasDto
import polkauction.core.model.dto.sidecar.ParasLeaseInfoDto
import java.io.FileNotFoundException
import java.util.*


class SidecarClient(private val chain: String) : ISidecarClient {

    private val ACCEPTED_CHAINS = listOf("Kusama", "Polkadot")

    private val PARACHAIN_PATH = "/experimental/paras/"
    private val PARACHAIN_LEASE_PATH_SUFFIX = "/lease-info"
    private val AUCTION_PATH = "/experimental/paras/auctions/current"

    private lateinit var baseUrl: String

    init{
        if(ACCEPTED_CHAINS.map{it.toUpperCase()} .indexOf(chain.toUpperCase()) < 0)
            throw NoSuchChainException("The chain $chain is not supported.")
        getBaseUrlFromProperties()
    }

    private fun getBaseUrlFromProperties() {
        val props = Properties()
        val propFileName = "chains.properties"

        val inputStream = javaClass.classLoader.getResourceAsStream(propFileName)

        if (inputStream != null) {
            props.load(inputStream)
        } else {
            throw FileNotFoundException("property file '$propFileName' not found in the classpath")
        }

        baseUrl = props.getProperty("${chain.toLowerCase()}.baseurl")
    }

    private val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = JacksonSerializer() {
                registerModule(GuavaModule())
                registerModule(kotlinModule())
            }
        }
        install(Logging){
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    override suspend fun getParas(): ParasDto {
        return client.get(baseUrl+PARACHAIN_PATH)
    }

    override suspend fun getParaLeaseInfo(paraId : Number): ParasLeaseInfoDto {
        return client.get(baseUrl+PARACHAIN_PATH+paraId+PARACHAIN_LEASE_PATH_SUFFIX)
    }

    override suspend fun getAuction(): Auction {
        return client.get(baseUrl+AUCTION_PATH)
    }

}