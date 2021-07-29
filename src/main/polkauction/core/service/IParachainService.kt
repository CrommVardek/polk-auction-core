package polkauction.core.service

import polkauction.core.model.Parachain

interface IParachainService {

    suspend fun getAllCurrentParachains(chain: String) : List<Parachain>

    suspend fun getParachain(chain: String, id: Number)  : Parachain?

}