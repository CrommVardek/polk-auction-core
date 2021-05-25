package vardek.polkauction.core.service

import vardek.polkauction.core.model.Parachain

interface IParachainService {

    suspend fun GetAllCurrentParachains() : List<Parachain>

}