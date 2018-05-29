package proxy

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy

fun main(args: Array<String>) {

    val clazzProxy1 = Proxy.getProxyClass(Collection::class.java.classLoader, Collection::class.java)
    println(clazzProxy1.name)

    println("------------------- begin constructors list ------------------- ")
    //$Proxy0()
    //$Proxy0(InvocationHandler, int)
    val constructors = clazzProxy1.constructors
    constructors.forEach {
        val name = it.name
        val sBuilder = StringBuilder(name)
        sBuilder.append("(")
        val clazzParams = it.parameterTypes
        clazzParams.forEach { clazzParam ->
            sBuilder.append(clazzParam.name).append(",")
        }
        if (clazzParams != null && clazzParams.isNotEmpty()) {
            sBuilder.deleteCharAt(sBuilder.length - 1)
        }
        sBuilder.append(")")

        println(sBuilder)
    }


    val clazzProxy2 = Proxy.getProxyClass(Collection::class.java.classLoader, Collection::class.java)
    println(clazzProxy2.name)
    println("------------------- begin constructors list ------------------- ")
    val methods = clazzProxy2.methods
    methods.forEach {
        val name = it.name
        val sBuilder = StringBuilder(name)
        sBuilder.append("(")
        val clazzParams = it.parameterTypes
        clazzParams.forEach { clazzParam ->
            sBuilder.append(clazzParam.name).append(",")
        }
        if (clazzParams != null && clazzParams.isNotEmpty()) {
            sBuilder.deleteCharAt(sBuilder.length - 1)
        }
        sBuilder.append(")")

        println(sBuilder)
    }

    println("------------------- begin create instance object ------------------- ")
   /* val clazzProxy3 = Proxy.getProxyClass(Collection::class.java.classLoader, Collection::class.java)
    val constructors2 = clazzProxy3.getConstructor(InvocationHandler::class.java)

    val invocationHandler = MyInvocationHandler()
    val proxy1 = constructors2.newInstance(invocationHandler) as Collection<*>
    println(proxy1)
*/

    println("------------------- begin create instance object ------------------- ")

  /*  val proxy3 = Proxy.newProxyInstance(Collection::class.java.classLoader,
            arrayOf(Collection::class.java),
            InvocationHandler { proxy, method, args ->
                invocationHandler
            }
    ) as Collection<*>
    proxy3.apply {
        "xx"
        "bb"
    }
    println(proxy3.size)
*/

}

