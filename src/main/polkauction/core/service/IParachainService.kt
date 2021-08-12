package polkauction.core.service

import polkauction.core.model.ParachainExtended

interface IParachainService {

    suspend fun getAllCurrentParachains(chain: String): List<ParachainExtended>

    suspend fun getParachain(chain: String, id: Int): ParachainExtended?

}