package polkauction.core.polkot.api

interface IPolkotApi {

    fun rawRequest(rpcMethod: String): String

}