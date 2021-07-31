package polkauction.core.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class RuntimeSpecificationPropertiesTest {

    @ParameterizedTest(name = "tokenDecimal value {0} value should throw")
    @ValueSource(ints = [-1, 0])
    fun illegalRuntimeSpecPropTokenDecimalShouldThrowIllegalArgumentException(tokenDecimal: Int){
        val tokenSymbol = "DOT"
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            RuntimeSpecificationProperties(tokenDecimal, tokenSymbol)
        }
    }

}