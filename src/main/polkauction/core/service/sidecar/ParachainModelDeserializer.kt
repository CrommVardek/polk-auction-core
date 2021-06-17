package polkauction.core.service.sidecar

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import polkauction.core.model.Parachain


class ParachainModelDeserializer : JsonDeserializer<List<Parachain>>() {

    override fun deserialize(jsonParser: JsonParser, context: DeserializationContext): List<Parachain> {
        val node = jsonParser.readValueAsTree<JsonNode>()

        val objectMapper = ObjectMapper()

        val parasNode = node.get("paras")

        val paraList: List<Parachain> = mutableListOf()

        return paraList
    }
}