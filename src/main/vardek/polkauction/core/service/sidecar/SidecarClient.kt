package vardek.polkauction.core.service.sidecar

import com.fasterxml.jackson.datatype.guava.GuavaModule
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.json.JacksonSerializer
import vardek.polkauction.core.model.Parachain

class SidecarClient : ISidecarClient {

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

    private val baseUrl = "http://127.0.1.1:8080"

    private val PARACHAIN_PATH = "/experimental/paras"

    override suspend fun GetParas(): List<Parachain> {
        return client.get<List<Parachain>>(baseUrl+PARACHAIN_PATH)
    }

}