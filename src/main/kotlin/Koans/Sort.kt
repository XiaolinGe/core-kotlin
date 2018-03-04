package Koans

// Return a list of customers, sorted by the ascending number of orders they made
fun Shop.getCustomersSortedByNumberOfOrders(): List<Customer> =
        customers.sortedBy {
            it.orders.size
        }

fun main(args: Array<String>) {
    val list = listOf("aa","b","c","t","e")

    val l1 = list.sorted()
    val l2 = list.sortedDescending()
    val l3 = list.sortedBy { it.length }
    val l4 = list.sortedByDescending { it.length }
    println(list)
    println(l1)
    println(l2)
    println(l3)
    println(l4
    )
}
