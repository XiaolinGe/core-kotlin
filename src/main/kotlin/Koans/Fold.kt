package Koans

// Return the set of products that were ordered by every customer
fun Shop.getSetOfProductsOrderedByEveryCustomer1(): Set<Product> {
    val allProduct = customers
            .flatMap { it.orders }
            .flatMap { it.products }.toSet()

    return allProduct.filter { product ->

        customers.all {
            it.orders.any {
                it.products.filter { it == product }.isNotEmpty()
            }
        }

    }.toSet()

}

fun Shop.getSetOfProductsOrderedByEveryCustomer(): Set<Product> {
    val allProducts = customers.flatMap { it.orders.flatMap { it.products }}.toSet()
    return customers.fold(allProducts, {
        orderedByAll, customer ->
        orderedByAll.intersect(customer.orders.flatMap { it.products }.toSet())
    })
}


fun main(args: Array<String>) {
   val l =  listOf(1, 2, 3, 4).fold(1, {
        partProduct, element ->
        element * partProduct
    })

    println(l)

    val s1 = setOf(1,2,3)
    val s2 = setOf(2,3,4)
    val s = s1.intersect(s2)
    println(s)

}
