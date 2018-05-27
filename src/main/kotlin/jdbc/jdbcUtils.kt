package jdbc

import com.google.common.base.CaseFormat
import jdbc.bean.Role
import java.io.File
import java.io.FileInputStream
import java.lang.UnsupportedOperationException
import java.sql.*
import java.util.*


/**
 * Program to list databases in MySQL using Kotlin
 */


var conn: Connection? = null


/**
 * This method makes a connection to MySQL Server
 * In this example, MySQL Server is running in the local host (so 127.0.0.1)
 * at the standard port 3306
 */
fun getConnection() {

    val rootPath = Thread.currentThread().contextClassLoader.getResource("")!!.path
    val connectionConfigPath = "${File(rootPath).parent}/resources/generator/local.properties"
    val connectionProps = Properties()
    connectionProps.load(FileInputStream(connectionConfigPath))

    /*
    val jdbcUser = connectionProps.getProperty("jdbcUser")
    val jdbcPassword = connectionProps.getProperty("jdbcPassword")
    val jdbcDriver = connectionProps.getProperty("jdbcDriver")
    val jdbcUrl = connectionProps.getProperty("jdbcUrl")

*/

    val jdbcDriver = "com.mysql.jdbc.Driver"
    val jdbcUser = "root"
    val jdbcPassword = "root"
    val jdbcUrl = "jdbc:mysql://127.0.0.1:3306/database"


    connectionProps["user"] = jdbcUser
    connectionProps["password"] = jdbcPassword
    try {
        Class.forName(jdbcDriver).newInstance()
        conn = DriverManager.getConnection(jdbcUrl, connectionProps)

    } catch (ex: SQLException) {
        // handle any errors
        ex.printStackTrace()
    } catch (ex: Exception) {
        // handle any errors
        ex.printStackTrace()
    }
}


val clazzMapper: (resultSet: ResultSet, clazz: Class<*>) -> List<Any> = { resultSet: ResultSet, clazz: Class<*> ->
    val beanList = mutableListOf<Any>()
    while (resultSet.next()) {
        val bean = clazz.newInstance()
        clazz.declaredFields.forEach {
            val name = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE).convert(it.name)
            val obj = when (it.type) {
                java.lang.Long::class.java -> resultSet.getLong(name)
                java.lang.String::class.java -> resultSet.getString(name)
                else -> throw UnsupportedOperationException("${it.type}")
            }
            it.isAccessible = true
            it.set(bean, obj)
            it.isAccessible = false
        }
        beanList.add(bean)
    }
    beanList
}

fun <T> executeSql(sql: String, clazz: Class<*>, mapper: (resultSet: ResultSet, clazz: Class<*>) -> List<T>): List<T> {
    getConnection()
    val stmt: Statement? = conn!!.createStatement()
    var resultSet = stmt!!.executeQuery(sql)
    if (stmt.execute(sql)) {
        resultSet = stmt.resultSet
    }
    return mapper(resultSet, clazz)
}

fun main(args: Array<String>) {
    val ex = executeSql("SELECT * FROM aci_role", Role::class.java, clazzMapper)
    println(ex)
}