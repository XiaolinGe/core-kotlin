package jooq

import org.joor.Reflect.*
import jdk.nashorn.internal.objects.NativeFunction.call
import jooq.bean.Employee
import org.joor.Reflect


fun main(args: Array<String>) {
    var world = Reflect.on(String::class.java) // Like Class.forName()
            .create("Hello World") // Call most specific matching constructor
            .call("substring", 6)  // Call most specific matching substring() method
            .call("toString")      // Call toString()
            .get<String>()

    println(world)

    createEmployeeObj()
    doMethod()
}

fun createEmployeeObj():Employee {
    val obj = Employee()
    Reflect.on(obj)
    Reflect.on(Employee::class.java)
    val e:Employee = on("jooq.jpa.bean.Employee")
            .create()
            .get()

    println(e)
    return e
}

fun doMethod() {
    val res = on("jooq.jpa.bean.Employee")
            .create()
            .call("setTheName",  "new")
            .get<Employee>()
            .name


    println(res)

}