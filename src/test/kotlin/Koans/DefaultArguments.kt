package Koans

import org.junit.Test
import org.junit.Assert

class TestDefaultAndNamedParams {

    fun useFoo() = listOf(
            foo("a"),
            foo("b", number = 1),
            foo("c", toUpperCase = true),
            foo(name = "d", number = 2, toUpperCase = true)
    )

    @Test
    fun testDefaultAndNamedParams() {
        Assert.assertEquals(listOf("a42", "b1", "C42", "D2"), useFoo())
    }
}