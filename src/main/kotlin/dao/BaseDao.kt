package dao

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


}




