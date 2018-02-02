package Koans

import org.junit.Test
import org.junit.Assert

class TestStringTemplates {
    @Test
    fun getPattern() {
        Assert.assertEquals("13 JUN 1992", "13.06.1992".getPattern())
    }

    @Test
    fun getPattern2() {
        Assert.assertEquals("13 DEC 1992", "13.12.1992".getPattern())
    }

    @Test
    fun getPatternInvalid() {
        Assert.assertEquals("13 invalid month 1992", "13.20.1992".getPattern())
    }

}