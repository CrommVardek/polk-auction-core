package polkauction.core.polkot.ws

import java.net.http.WebSocket
import java.util.concurrent.CompletionStage

class PolkadotWebsocketListener: WebSocket.Listener {

    override fun onOpen(webSocket: WebSocket?) {
        super.onOpen(webSocket)
    }

    override fun onClose(webSocket: WebSocket?, statusCode: Int, reason: String?): CompletionStage<*> {
        return super.onClose(webSocket, statusCode, reason)
    }

    override fun onError(webSocket: WebSocket?, error: Throwable?) {
        super.onError(webSocket, error)
    }

    override fun onText(webSocket: WebSocket?, data: CharSequence?, last: Boolean): CompletionStage<*> {
        return super.onText(webSocket, data, last)
    }

}