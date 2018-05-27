package generics

fun main(args: Array<String>) {
    val result1: List<String> = executeSql("select * from student",{ listOf(it)})
    println(result1)
    println("----------")
    var result2: List<Int> = executeSql("select * from foo",{ listOf(it)})
    println(result2)
}

fun <T> executeSql(sql: String, mapper: Mapper): List<T> {
    var list: List<T> = emptyList()
    println("Get a connection")
    println("open a trx")
    println("exec a sql")
    val result = mapper(sql)
    println("close a trx")
    println("Release a connection")

    result.forEach {
        list += it as T
    }
    return list
}



typealias Mapper = (resultSet: String) -> List<Any>

//Rowmapper