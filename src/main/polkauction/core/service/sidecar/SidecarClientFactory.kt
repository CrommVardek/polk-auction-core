package polkauction.core.service.sidecar

interface ISidecarClientFactory {
    fun getSidecarClient(chain: String) : ISidecarClient
}

class SidecarClientFactory: ISidecarClientFactory {

    override fun getSidecarClient(chain: String) : ISidecarClient {
        return SidecarClient(chain)
    }
}