package Koans

import org.junit.Assert
import org.junit.Test

class TestGroupBy {
    @Test fun testGroupCustomersByCity() {
        println(shop.groupCustomersByCity())

        Assert.assertEquals(groupedByCities, shop.groupCustomersByCity())
    }
}

