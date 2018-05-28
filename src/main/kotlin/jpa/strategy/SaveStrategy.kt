package jpa.strategy

import Koans.map
import com.google.common.base.CaseFormat
import jdbc.conn
import jdbc.getConnection
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement


val SaveOneMapper: (resultSet: ResultSet, clazz: Class<*>) -> Any = { resultSet, clazz ->
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


fun <T> saveOneSql(sql: String, clazz: Class<*>, mapper: (resultSet: ResultSet, clazz: Class<*>) -> T?): T? {
    getConnection()

    try {
        conn!!.autoCommit = false
        val stmt: Statement? = conn!!.createStatement()
        stmt!!.executeUpdate(sql)
        conn!!.commit()
        conn!!.autoCommit = true
    } catch (e: SQLException) {
        try {
            conn!!.rollback()
            conn!!.close()
        } catch (e1: SQLException) {
            e1.printStackTrace()
        }
    }
    return null

}


