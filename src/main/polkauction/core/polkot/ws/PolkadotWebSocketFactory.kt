package polkauction.core.polkot.ws

import java.net.URI
import java.net.http.HttpClient
import java.net.http.WebSocket
import java.util.concurrent.CompletableFuture

class PolkadotWebSocketFactory {
    fun newWebSocket(listener: PolkadotWebsocketListener): WebSocket {
        val client: HttpClient = HttpClient.newHttpClient()
        val ws = client.newWebSocketBuilder()
            .buildAsync(URI.create("ws://websocket.example.com"), listener)
            .join()
        return ws
    }
}