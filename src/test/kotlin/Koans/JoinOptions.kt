package Koans

import org.junit.Assert
import org.junit.Test

class TestNamedArguments {

    @Test
    fun testJoinToString() {
        Assert.assertEquals("[yes, no, may be]", joinOptions(listOf("yes", "no", "may be")))
    }
}
