package Koans


fun getSet(): Set<String> {
   val list = listOf<String>("a", "b", "c", "a")
    return list.toSet()
}

fun main(args: Array<String>) {
    val list = listOf<String>("a", "b", "c", "a")
    println(list.toSet())
    println(list.filter { "a".equals(it) })
    println(list.map { "$it is good" })
    println(list.any { it == "v" })
    println(list.sorted())
    println(list.withIndex().map { it.index })

}