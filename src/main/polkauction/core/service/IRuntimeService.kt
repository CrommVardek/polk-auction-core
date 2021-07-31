package polkauction.core.service

import polkauction.core.model.RuntimeSpecification

interface IRuntimeService {
    suspend fun getSpecification(chain: String): RuntimeSpecification
}