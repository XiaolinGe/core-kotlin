package Koans

fun joinOptions(options: Collection<String>): String {
    val sb = StringBuilder()
    sb.append("[")
    val iterator = options.iterator()
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