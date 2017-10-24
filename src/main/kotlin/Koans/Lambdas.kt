package Koans

fun containsEven(collection: Collection<Int>): Boolean = collection.any {
    collection.any { it % 2 == 0 }
}

fun main(args: Array<String>) {
    println(containsEven(listOf(2,4,6)))
    println(containsEven(listOf(1,2)))
    println(containsEven(listOf(1,3)))
}
