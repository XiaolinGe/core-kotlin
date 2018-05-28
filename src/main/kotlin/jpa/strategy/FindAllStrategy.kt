package jpa.strategy

import com.google.common.base.CaseFormat
import jdbc.conn
import jdbc.getConnection
import java.lang.UnsupportedOperationException
import java.sql.ResultSet
import java.sql.Statement


val FindAllMapper: (resultSet: ResultSet, clazz: Class<*>) -> List<Any> = { resultSet: ResultSet, clazz: Class<*> ->
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

fun <T> findAllSql(sql: String, clazz: Class<*>, mapper: (resultSet: ResultSet, clazz: Class<*>) -> List<T>): List<T> {
    getConnection()
    val stmt: Statement? = conn!!.createStatement()
    var resultSet = stmt!!.executeQuery(sql)
    if (stmt.execute(sql)) {
        resultSet = stmt.resultSet
    }
    return mapper(resultSet, clazz)
}