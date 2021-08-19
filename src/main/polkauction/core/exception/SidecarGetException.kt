package polkauction.core.exception

import io.ktor.client.statement.*

class SidecarGetException(exceptionResponseText: String) : Exception(exceptionResponseText) {

}
