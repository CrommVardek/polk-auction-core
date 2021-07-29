package polkauction.core.service.sidecar

fun getSidecarClient(chain: String) : ISidecarClient {
    return SidecarClient(chain);
}