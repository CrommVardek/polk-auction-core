package vardek.polkauction.core.service.sidecar

import com.fasterxml.jackson.datatype.guava.GuavaModule
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import vardek.polkauction.core.exception.NoSuchChainException
import vardek.polkauction.core.model.Parachain
import java.io.FileNotFoundException
import java.util.*


class SidecarClient(private val chain: String) : ISidecarClient {

    private val ACCEPTED_CHAINS = listOf("Kusama", "Polkadot")

    private val PARACHAIN_PATH = "/experimental/paras"

    private var baseUrl: String

    init{
        if(ACCEPTED_CHAINS.map{it.toUpperCase()} .indexOf(chain.toUpperCase()) < 0)
            throw NoSuchChainException("The chain $chain is not supported.")
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
            }
        }
        install(Logging){
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    override suspend fun GetParas(): List<Parachain> {
        return client.get(baseUrl+PARACHAIN_PATH)
    }

}