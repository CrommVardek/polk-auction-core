package polkauction.core.service

import polkauction.core.model.Parachain

interface IParachainService {

    suspend fun GetAllCurrentParachains() : List<Parachain>

}