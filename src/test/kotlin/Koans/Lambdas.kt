package Koans

import org.junit.Test
import org.junit.Assert

class TestLambdas {
    @Test
    fun contains() {
        Assert.assertTrue("true", containsEven(listOf(1, 2, 3, 126, 555)))
    }

    @Test
    fun notContains() {
        Assert.assertFalse("false", containsEven(listOf(43, 33)))
    }
}