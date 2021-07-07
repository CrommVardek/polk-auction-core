package polkauction.core.service

import polkauction.core.model.Parachain

interface IParachainService {

    suspend fun getAllCurrentParachains() : List<Parachain>

    suspend fun getParachain(id: Number)  : Parachain?

}