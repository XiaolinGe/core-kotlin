package Koans

data class Person (val name: String, val age: Int)

fun getPeople(): List<Person> = listOf(Person("Alice", 29), Person("Bob", 31))

fun main(args: Array<String>) {
    println(getPeople())
}