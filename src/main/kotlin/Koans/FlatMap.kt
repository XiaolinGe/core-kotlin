package Koans

// Return all products this customer has ordered
val Customer.orderedProducts: Set<Product>
    get() {
        val res = orders.flatMap { it.products }.toSet()
        println(res)
        return res
    }

// Return all products that were ordered by at least one customer
val Shop.allOrderedProducts: Set<Product>
    get() {
        val s = customers.flatMap { it.orderedProducts }.toSet()
        println(s)
        return s

    }