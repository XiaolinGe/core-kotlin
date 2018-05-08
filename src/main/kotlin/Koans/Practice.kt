package Koans

fun main(args: Array<String>) {
    val list = listOf(1, 2, 3, 4)
// 如果你想通过 list 或者 array 的索引进行迭代，你可以这样做：for (i in array.indices)
// http://stackoverflow.org.cn/front/ask/view?ask_id=80049
    for (i in list.indices) {
        println("${list[i]} : $i ")
    }

}