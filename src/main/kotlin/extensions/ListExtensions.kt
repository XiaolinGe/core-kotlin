package extensions


fun List<Int>.toJson(): String {
    val sb = StringBuilder()
    sb.append("[")
    val iterator = this.iterator()
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
  print(listOf(1,2,3,5).toJson())
}
