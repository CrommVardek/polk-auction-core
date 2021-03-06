package polkauction.core.repository

import polkauction.core.model.entities.Parachain

interface IParachainRepository {
    suspend fun getAllFor(relayChain: String) : List<Parachain>
    suspend fun getByIdFor(parachainId: Int, relayChain: String): Parachain?
}