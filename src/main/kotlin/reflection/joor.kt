package reflection

import org.joor.Reflect.*

fun main(args: Array<String>) {
    println(world)
    println(substring)
}


var world = on("java.lang.String")  // Like Class.forName()
        .create("Hello World") // Call most specific matching constructor
        .call("substring", 6)  // Call most specific matching substring() method
        .call("toString")      // Call toString()
        //.get()

interface StringProxy {
    fun substring(beginIndex: Int): String
}

var substring = on("java.lang.String")
        .create("Hello World")
        .`as`(StringProxy::class.java) // Create a proxy for the wrapped object
        .substring(6)