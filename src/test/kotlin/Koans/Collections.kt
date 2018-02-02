package Koans

import org.junit.Assert
import org.junit.Test


class TestIntroduction {
    @Test fun testSetOfCustomers(){
        Assert.assertEquals(customers.values.toSet(), shop.getSetOfCustomers())
    }
}
