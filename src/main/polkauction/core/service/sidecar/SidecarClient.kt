package polkauction.core.service.sidecar

import com.fasterxml.jackson.databind.util.JSONPObject
import com.fasterxml.jackson.datatype.guava.GuavaModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.JsonObject
import polkauction.core.exception.NoSuchChainException
import polkauction.core.exception.SidecarGetException
import polkauction.core.model.EcoSystemConstants.KUSAMA_CHAIN_NAME
import polkauction.core.model.EcoSystemConstants.POLKADOT_CHAIN_NAME
import polkauction.core.model.dto.sidecar.*
import java.io.FileNotFoundException
import java.util.*


class SidecarClient(private val chain: String) : ISidecarClient {

    private val ACCEPTED_CHAINS = listOf(POLKADOT_CHAIN_NAME, KUSAMA_CHAIN_NAME)

    private val PARACHAIN_PATH = "/paras/"
    private val PARACHAIN_LEASE_PATH_SUFFIX = "/lease-info"
    private val AUCTION_PATH = PARACHAIN_PATH + "auctions/current"
    private val CROWDLOAN_PATH = PARACHAIN_PATH + "crowdloans"
    private val RUNTIME_PATH = "/runtime"
    private val RUNTIME_SPEC_PATH = RUNTIME_PATH + "/spec"
    private val METADATA_PATH = RUNTIME_PATH + "/metadata"
    private val BLOCK_PATH = "/blocks/"
    private val BLOCK_HEAD_PATH = BLOCK_PATH + "head"

    private lateinit var baseUrl: String

    init{
        if(ACCEPTED_CHAINS.map{it.toUpperCase()} .indexOf(chain.toUpperCase()) < 0)
            throw NoSuchChainException("The chain $chain is not supported.")
        getBaseUrlFromProperties()
    }

    private fun getBaseUrlFromProperties() {
        val props = Properties()
        val env = System.getenv("POLKAUCTION_ENV")
        val propFileName = "chains.$env.properties"

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
            serializer = JacksonSerializer {
                registerModule(GuavaModule())
                registerModule(kotlinModule())
            }
        }
        install(Logging){
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(HttpTimeout)
        HttpResponseValidator {
            handleResponseException { exception ->
                if (exception !is ClientRequestException) return@handleResponseException
                val exceptionResponse = exception.response
                if (exceptionResponse.status.value >= HttpStatusCode.InternalServerError.value) {
                    val exceptionResponseText = exceptionResponse.readText()
                    throw SidecarGetException(exceptionResponseText)
                }
            }
        }
    }

    override suspend fun getParas(): ParasDto {
        return client.get(baseUrl+PARACHAIN_PATH)
    }

    override suspend fun getParaLeaseInfo(paraId : Number): ParasLeaseInfoDto {
        return client.get(baseUrl+PARACHAIN_PATH+paraId+PARACHAIN_LEASE_PATH_SUFFIX)
    }

    override suspend fun getAuction(): AuctionDto {
        return client.get(baseUrl+AUCTION_PATH)
    }

    override suspend fun getCrowdloan(): ParasCrowdloansDto {
        return client.get(baseUrl+CROWDLOAN_PATH)
    }

    override suspend fun getRuntimeSpecification(): RuntimeSpecificationDto {
        return client.get(baseUrl+RUNTIME_SPEC_PATH)
    }

    override suspend fun getMetadata(): RuntimeMetadataDto {
        return client.get(baseUrl+METADATA_PATH)
    }

    override suspend fun getBlockAt(height: Number): BlockDto {
        return client.get(baseUrl+BLOCK_PATH+height) {
            timeout {
                requestTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
            }
        }
    }

    override suspend fun getBlockHead(): BlockDto {
        return client.get(baseUrl+BLOCK_HEAD_PATH)
    }

}