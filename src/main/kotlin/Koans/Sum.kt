package Koans

// Return the sum of prices of all products that a customer has ordered.
// Note: the customer may order the same product for several times.
fun Customer.getTotalOrderPrice(): Double = orders
        .flatMap{ it.products }
        .sumByDouble { it.price }

fun main(args: Array<String>) {
    val list = listOf(1,2,3,4,5)
    val l1 = list.sum()
    val l2 = list.sumBy { it }
    val l3 = list.sumByDouble { it.toDouble() }

    println(l1)
    println(l2)
    println(l3)
}

