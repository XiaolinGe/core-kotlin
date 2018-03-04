package Koans

import org.junit.Assert
import org.junit.Test

class TestSum {
    @Test fun testGetTotalOrderPrice() {
        Assert.assertEquals(148.0, customers[nathan]!!.getTotalOrderPrice(), 0.001)
    }

    @Test fun testTotalPriceForRepeatedProducts() {
        Assert.assertEquals(586.0, customers[lucas]!!.getTotalOrderPrice(), 0.001)
    }
}
