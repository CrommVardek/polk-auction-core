package polkauction.core.model

data class RuntimeSpecificationProperties(val tokenDecimal: Int, val tokenSymbol: String) {
    init {
        require(tokenDecimal > 0) { "TokenDecimal must be greater than 0" }
    }
}
