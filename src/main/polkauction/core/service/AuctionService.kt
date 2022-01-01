package polkauction.core.service

import polkauction.core.model.Auction
import polkauction.core.model.entities.LeasePeriod
import polkauction.core.model.mapper.toAuction
import polkauction.core.model.with
import polkauction.core.repository.IParachainRepository
import polkauction.core.service.sidecar.ISidecarClientFactory

class AuctionService(
    private val parachainRepository: IParachainRepository,
    private val sidecarClientFactory: ISidecarClientFactory,
    private val leasePeriodService: ILeasePeriodService,
) : IAuctionService {

    override suspend fun getCurrentAuction(chain: String): Auction {
        val registeredParachains = parachainRepository.getAllFor(chain.toLowerCase().capitalize())
        val sidecarClient = sidecarClientFactory.getSidecarClient(chain)
        var auctionRaw = sidecarClient.getAuction()
        auctionRaw.winning = auctionRaw.winning?.filter { it.bid != null }
        val auctionLeasePeriodFilter: (LeasePeriod) -> Boolean = { lp -> auctionRaw.leasePeriods?.any{it == lp.period.toString() }?: false}
        val leasePeriods = leasePeriodService.getFilteredFor(chain, auctionLeasePeriodFilter)
        //val metadata = sidecarClient.getMetadata()
        //val slotsPallet = metadata.metadata.v14.pallets.single { it.name == "Slots" }
        //val leaseOffset = slotsPallet.constants.single { it.name == "leaseOffset"}
        //val leasePeriod = slotsPallet.constants.single { it.name == "leasePeriod"}
        return auctionRaw.toAuction().with(registeredParachains, leasePeriods)
    }


}