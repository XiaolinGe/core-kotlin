package Koans

fun foo(name: String, number: Int = 42, toUpperCase: Boolean = false): String =
        (if (toUpperCase) name.toUpperCase() else name) + number



fun main(args: Array<String>) {
    fun useFoo() = listOf(
            foo("a"),
            foo("b", number = 1),
            foo("c", toUpperCase = true),
            foo(name = "d", number = 2, toUpperCase = true)
    )

    println(useFoo())

    println(foo("x"))

}

