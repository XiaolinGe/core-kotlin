package proxy

import java.lang.reflect.Method

public class MyAdvice: Advice {
    var beginTime = 0.toLong()

    override fun beforeMethod( method: Method ) {
        println("开始啦")
        beginTime = System.currentTimeMillis()
    }

    override fun afterMethod( method: Method ) {
        println("毕业啦")
        val endTime = System.currentTimeMillis()
        println(method.name + "running time is: " + (beginTime - endTime ))
    }


}