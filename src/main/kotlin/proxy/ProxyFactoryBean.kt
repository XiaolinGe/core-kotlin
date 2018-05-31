package proxy


import java.io.IOException
import java.io.InputStream
import java.util.Properties

class ProxyFactoryBean @Throws(IOException::class)
constructor(ips: InputStream) {
    internal var props = Properties()

    init {
        props.load(ips)
    }

    fun getBean(name: String): Any? {
        val className = props.getProperty(name)
        var bean: Any? = null
        var clazz: Class<*>? = null
        try {
            clazz = Class.forName(className)
            bean = clazz!!.newInstance()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return if (bean is ProxyFactoryBean) {
            (bean as ProxyFactoryBean).getProxy()
        } else bean
    }

    fun getProxy(): Any? {
        return null
    }
}