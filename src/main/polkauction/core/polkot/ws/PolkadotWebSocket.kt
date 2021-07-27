package polkauction.core.polkot.ws

import java.net.URI
import java.net.http.HttpClient
import java.net.http.WebSocket
import java.nio.ByteBuffer
import java.util.concurrent.CompletableFuture


class PolkadotWebSocket: WebSocket {

    private var polkadotListener : PolkadotWebsocketListener
    private var websocket : WebSocket

    // {"jsonrpc":"2.0","method":"system_chain","id":0}

    constructor(url: String, listener: PolkadotWebsocketListener){
        polkadotListener = listener
        val client: HttpClient = HttpClient.newHttpClient()
        websocket = client.newWebSocketBuilder()
            .buildAsync(URI.create(url), listener)
            .join()
    }

    override fun sendText(data: CharSequence?, last: Boolean): CompletableFuture<WebSocket> {
        return  websocket.sendText(data, last)
    }

    override fun sendBinary(data: ByteBuffer?, last: Boolean): CompletableFuture<WebSocket> {
        TODO("Not yet implemented")
    }

    override fun sendPing(message: ByteBuffer?): CompletableFuture<WebSocket> {
        TODO("Not yet implemented")
    }

    override fun sendPong(message: ByteBuffer?): CompletableFuture<WebSocket> {
        TODO("Not yet implemented")
    }

    override fun sendClose(statusCode: Int, reason: String?): CompletableFuture<WebSocket> {
        TODO("Not yet implemented")
    }

    override fun request(n: Long) {
        TODO("Not yet implemented")
    }

    override fun getSubprotocol(): String {
        TODO("Not yet implemented")
    }

    override fun isOutputClosed(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isInputClosed(): Boolean {
        TODO("Not yet implemented")
    }

    override fun abort() {
        TODO("Not yet implemented")
    }


}

