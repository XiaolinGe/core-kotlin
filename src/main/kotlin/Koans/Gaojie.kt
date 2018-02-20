package Koans

// 高阶函数(high-order function): 参数或者返回值为function
fun MutableMap<String, String>.myGetOrElse(key: String, fallback: () -> Any): Any {
    val value = this[key]
    return when (value) {
        null -> {
            fallback()
        }
        else -> value
    }
}


fun MutableMap<String, String>.myGetOrPut(key: String, fallback: () -> Any): Any {
    val value = this[key]
    return when(value) {
        null -> {
            val res = fallback()
            this[key] = res.toString()
            res

        }else -> value
    }
}


fun MutableMap<String, String>.myGetOrPutWithParam(key: String, fallback: (str: String, str2: String) -> Any): Any {
    val value = this[key]
    return when(value) {
        null -> {
            val res = fallback("aaa", "bbb")
            this[key] = res.toString()
            res

        }else -> value
    }
}

fun main(args: Array<String>) {
    val myMap:MutableMap<String, String> = mutableMapOf("1" to "a", "2" to "b")

    println("==================myGetOrElse======================")
    println(myMap.myGetOrElse("1" ){"invalid key"})
    println(myMap.myGetOrElse("2" ){"invalid key"})
    println(myMap.myGetOrElse("3" ){"invalid key"})
    println(myMap)
    println("==================myGetOrPut======================")
    println(myMap.myGetOrPut("1") {"the new value"})
    println(myMap.myGetOrPut("2") {"the new value"})
    println(myMap.myGetOrPut("3") {"the new value"})
    println(myMap.myGetOrPut("3") {"the new value"})
    println(myMap)

    println("==================myGetOrPutWithParam======================")
    val test = fun (a: String, b: String ) {"this is fun"}
    println(myMap.myGetOrPutWithParam("5", test))
    println(myMap.myGetOrPutWithParam("5",  { a, b -> "this is 5" } ))

    println(myMap)

}