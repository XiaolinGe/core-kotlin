package Koans


fun Shop.getSetOfCustomers(): Set<Customer> {
    println(customers)
    return customers.toSet()
}