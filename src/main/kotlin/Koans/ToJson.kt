package Koans

fun toJSON(collection: Collection<Int>): String {
    val sb = StringBuilder()
    sb.append("[")
    val iterator = collection.iterator()
    while (iterator.hasNext()) {
        val element = iterator.next()
        sb.append(element)
        if (iterator.hasNext()) {
            sb.append(", ")
        }
    }
    sb.append("]")
    return sb.toString()
}

fun main(args: Array<String>) {
    toJSON(listOf(1,2,3,4))
}
