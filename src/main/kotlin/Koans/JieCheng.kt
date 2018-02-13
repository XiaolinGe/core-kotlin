package Koans

val map: MutableMap<Int, Int> = mutableMapOf()

fun suan(n: Int): Int {
    if (n == 0) return 1
    var result = map.get(n)
    if (result != null) {
        return result
    } else {
        result = n * suan(n - 1)
        map.put(n, result)
        return result
    }
}


fun jia(param: Int): Int {
    suan(param)
    return map.map { it.value }.reduce { a, b -> a + b }

}


/*========================================================*/

val myMap: MutableMap<Int, Int> = mutableMapOf()

fun jiecheng1(n: Int): Int {
    return when (n) {
        1 -> 1
        else -> {
            var res: Int? = myMap[n]
            return if (res != null) {
                res
            } else {
                res = n * jiecheng1(n - 1)
                myMap.put(n, res)
                res
            }

        }

    }
}


fun jiecheng2(n: Int): Int {
    return when (n) {
        1 -> 1
        else -> {
            myMap.getOrElse(n) {
                val res = n * jiecheng2(n - 1)
                myMap.put(n, res)
                res
            }
        }
    }
}

fun jiecheng3(n: Int): Int {
    return when (n) {
        1 -> 1
        else -> {
            myMap.getOrPut(n) { n * jiecheng3(n - 1) }
        }
    }
}


fun sum1(n: Int): Int {
    return (1..n).map { jiecheng1(it) }.sum()
}


fun sum2(n: Int): Int {
    return (1..n).map { jiecheng2(it) }.sum()
}


fun sum3(n: Int): Int {
    return (1..n).map { jiecheng3(it) }.sum()
}


fun getList(max: Int): List<Int> {
    // 用 take while 和 generateSequence
    return (1..max).takeWhile { jiecheng3(it) < max }
}


fun generate(): Sequence<Int> {
    val squrence = generateSequence(1) { it.inc() }
    return squrence
}


fun sum4(n: Int): Sequence<Int> {
    val list = generate()
    return list.takeWhile {
        println("filter $it")
        jiecheng3(it) < n
    }

}

fun sum5(n: Int): Sequence<Int> {
    val list = generate()
    return list.filter {
        println("filter $it")
        jiecheng3(it) < n
    }
}

fun main(args: Array<String>) {
//    println(sum1(3))
//    println(sum2(4))
//    println(sum3(5))
//
//    println(getList(10))
//    println(generate())
    println("======== takewhile =========")
    println(sum4(10).toList())

    println("======== filter =========")
     // println(sum5(10).toList())
}


