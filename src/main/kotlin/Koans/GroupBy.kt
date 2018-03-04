package Koans

// Return a map of the customers living in each city
fun Shop.groupCustomersByCity(): Map<City, List<Customer>> = customers
        .groupBy { it.city }

fun main(args: Array<String>) {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8)
    val l1 = list.groupBy {
        when (it % 2) {
            0 -> "even"
            else -> "odd"
        }
    }

    val l2 = list.groupingBy {
        when (it % 2) {
            0 -> "even"
            else -> "odd"
        }
    }.eachCount()



    println(l1)
    println(l2)
}