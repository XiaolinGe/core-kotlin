package jpa.dao

import jpa.annotation.id.getId
import jpa.annotation.primaryKey.getPrimaryKey
import jpa.bean.Role
import jpa.strategy.*
import java.lang.reflect.ParameterizedType

open class BaseDao<T> {

    private var tableName: String? = null
    private var clazz: Class<*>? = null

    fun baseDaoInfo() {
        val baseDaoClass = this::class.java
        val type = baseDaoClass.genericSuperclass
        val pt = type as ParameterizedType
        val actualType = pt.actualTypeArguments[0]
        this.clazz = actualType as Class<*>

        val className = actualType.typeName.toLowerCase()
        tableName = className.substring((className.lastIndexOf(".") + 1))

    }

    fun findOne(id: Int): T? {
        baseDaoInfo()
        val sql = "select * from aci_$tableName where id = $id;"
        return findOneSql(sql, this.clazz!!, FindOneMapper) as T
    }

    fun findAll(): List<T>? {
        baseDaoInfo()
        val sql = "select * from aci_$tableName;"
        return findAllSql(sql, this.clazz!!, FindAllMapper) as List<T>
    }

    fun deleteOne(id: Int):  Any? {
        baseDaoInfo()
        val sql = "delete from aci_$tableName where id = $id;"
        return deleteOneSql(sql, this.clazz!!, DeleteOneMapper)
    }

    fun saveOne(id: Int?): T? {
        baseDaoInfo()
        var sql = ""
        val clazz: Class<*>? = null
        //val idField = getId(clazz)
        sql = when(id) {
            null -> "INSERT INTO aci_role ( version, name, creator_id, modifier_id) VALUES (0, 'tester', 1, 1);"
            else -> "UPDATE aci_role SET name='111' where name='tester';"
        }
        return saveOneSql(sql, this.clazz!!, SaveOneMapper) as T
    }

}


/**
 * JDBC: java database connectivity, Java数据库连接
 * annotation: Id, PrimaryKey,
 * strategy: save (insert, update)
 * jdbc 事务
 * annotation: Table, Column, other
 *
 * */

fun main(args: Array<String>) {
    val role = Role::class.java
    val id = getId(role)
    val pk = getPrimaryKey(role)

    println(id)
    println(pk)
}
