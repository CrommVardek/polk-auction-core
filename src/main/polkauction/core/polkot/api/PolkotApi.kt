package polkauction.core.polkot.api

import polkauction.core.polkot.model.Block
import polkauction.core.polkot.ws.PolkadotWebSocket
import polkauction.core.polkot.ws.PolkadotWebsocketListener


class PolkotApi : AbstractPolkotApi, IPolkotApi{

    private val polkadotWebSocket: PolkadotWebSocket = TODO()
    private val polkadotWSListener: PolkadotWebsocketListener

    constructor(url: String) {
        polkadotWSListener = PolkadotWebsocketListener()
        polkadotWebSocket = PolkadotWebSocket(url, polkadotWSListener)
    }

    override fun rawRequest(rpcMethod: String): String {
        //TODO
        val result = polkadotWebSocket.sendText(rpcMethod, true)

        return "TODO"
    }


}