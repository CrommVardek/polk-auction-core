package polkauction.core.model

data class FundInfo(val depositor: String, val verifier: Verifier?, val deposit: Double, val raised: Double, val end: Long,
                    val cap: Double, val firstPeriod: String, val lastPeriod: String, val trieIndex: String, val state: FundState )
