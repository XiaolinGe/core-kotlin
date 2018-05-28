package jpa.strategy

import com.google.common.base.CaseFormat
import jdbc.conn
import jdbc.getConnection
import java.lang.UnsupportedOperationException
import java.sql.ResultSet
import java.sql.Statement

val DeleteOneMapper: (resultSet: ResultSet, clazz: Class<*>) -> Any = { resultSet: ResultSet, clazz: Class<*> ->
    val bean = clazz.newInstance()
    while (resultSet.next()) {
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
    }
    bean
}

fun  deleteOneSql(sql: String, clazz: Class<*>, mapper: (resultSet: ResultSet, clazz: Class<*>) ->  Any? ): Any? {
    getConnection()
    val stmt: Statement? = conn!!.createStatement()
    var resultSet = stmt!!.executeUpdate(sql)

  /*  if (stmt.execute(sql)) {
        resultSet = stmt.resultSet
    }
    mapper(resultSet, clazz)*/

    return resultSet
}