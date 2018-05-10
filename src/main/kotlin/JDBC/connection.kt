package JDBC

import JDBC.bean.*
import com.google.common.base.CaseFormat
import jdk.nashorn.internal.codegen.types.Type.isAssignableFrom
import java.lang.UnsupportedOperationException
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.sql.*
import java.util.Properties
import java.sql.ResultSet


/**
 * Program to list databases in MySQL using Kotlin
 */
object MySQLDatabaseExampleKotlin {

    var conn: Connection? = null
    var username = "root" // provide the username
    var password = "root" // provide the corresponding password

    @JvmStatic
    fun main(args: Array<String>) {
        // make a connection to MySQL Server
        getConnection()
        // execute the query via connection object
        executeMySQLQuery()
    }

    fun executeMySQLQuery() {
        var stmt: Statement? = null
        var resultset: ResultSet? = null

        try {
            val roleList = getRoles(getResultSet("role", stmt, resultset))
            val ruleList = getRules(getResultSet("rule", stmt, resultset))
            val permissionList = getPermissions("address")
            val rolePermissionList = getRolePermissions(roleList, permissionList)
            val rolePermissionRuleList = getRolePermissionRule(rolePermissionList, ruleList)
            val permissionSqlList = getPermissionSql(permissionList)
            val rolePermissionSqlList = getRolePermissionSql(rolePermissionList)
            val rolePermissionRuleSqlList = getRolePermissionRuleSql(rolePermissionRuleList)

            println(roleList)
            println(ruleList)
            println(permissionList)
            println(rolePermissionList)
            println(rolePermissionRuleList)
            println(permissionSqlList)
            println(rolePermissionSqlList)
            println(rolePermissionRuleSqlList)


        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } finally {
            // release resources
            if (resultset != null) {
                try {
                    resultset.close()
                } catch (sqlEx: SQLException) {
                }

                resultset = null
            }

            if (stmt != null) {
                try {
                    stmt.close()
                } catch (sqlEx: SQLException) {
                }

                stmt = null
            }

            if (conn != null) {
                try {
                    conn!!.close()
                } catch (sqlEx: SQLException) {
                }

                conn = null
            }
        }
    }

    /**
     * This method makes a connection to MySQL Server
     * In this example, MySQL Server is running in the local host (so 127.0.0.1)
     * at the standard port 3306
     */
    fun getConnection() {
        val connectionProps = Properties()
        connectionProps.put("user", username)
        connectionProps.put("password", password)
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance()
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/collinson", connectionProps)

        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } catch (ex: Exception) {
            // handle any errors
            ex.printStackTrace()
        }
    }

    fun getResultSet(beanName: String, statement: Statement?, resultset: ResultSet?): ResultSet {
        var stmt = statement
        var res = resultset
        stmt = conn!!.createStatement()
        val sql = "SELECT * FROM collinson.$beanName;"
        res = stmt!!.executeQuery(sql)

        if (stmt.execute(sql)) {
            res = stmt.resultSet

        }
        return res
    }

    fun getRoles(resultset: ResultSet): List<Role> {
        val roleList = mutableListOf<Role>()
        while (resultset.next()) {
            val role = JDBC.bean.Role()
            val roleFields = Role::class.java.declaredFields

            roleFields.forEach {
                val name = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE).convert(it.name)

                val res = when (it.type) {
                    java.lang.Long::class.java -> resultset.getLong(name)
                    java.lang.String::class.java -> resultset.getString(name)
                    else -> throw UnsupportedOperationException("${it.type}")
                }

                it.isAccessible = true
                it.set(role, res)
                it.isAccessible = false
            }

            /*
            role.id = resultset.getLong("id")
            role.version = resultset.getLong("version")
            role.name = resultset.getString("name")
            role.creatorId = resultset.getLong("creator_id")
            role.modifierId = resultset.getLong("modifier_id")
            */
            roleList.add(role)
        }
        return roleList
    }


    fun getRules(resultset: ResultSet): List<Rule> {
        val ruleList = mutableListOf<Rule>()
        while (resultset.next()) {
            val rule = JDBC.bean.Rule()
            rule.id = resultset.getLong("id")
            rule.version = resultset.getLong("version")
            rule.name = resultset.getString("name")
            rule.params = resultset.getString("params")
            rule.type = resultset.getString("type")
            rule.enable = resultset.getString("enable")
            ruleList.add(rule)
        }
        return ruleList
    }

    fun getPermissions(entityName: String): List<Permission> {

        val permissionList = mutableListOf<Permission>()
        val baseId = 1

        permissionList.add(indexPermission(entityName))
        permissionList.add(createPermission(entityName))
        permissionList.add(readPermission(entityName))
        permissionList.add(updatePermission(entityName))
        permissionList.add(deletePermission(entityName))

        permissionList.mapIndexed { index, permission ->
            permission.id = (baseId * 100 + index.inc()).toLong()
        }

        return permissionList

    }

    fun indexPermission(entityName: String): Permission {
        val permission = Permission()
        permission.version = 0
        permission.authKey = "Index $entityName"
        permission.authUris = "/v[\\d]+/$entityName"
        permission.display = "Index $entityName"
        permission.entity = entityName
        permission.httpMethod = "GET"
        permission.creatorId = 1
        permission.modifierId = 1
        return permission
    }

    fun createPermission(entityName: String): Permission {
        val permission = Permission()
        permission.version = 0
        permission.authKey = "Create $entityName"
        permission.authUris = "/v[\\d]+/$entityName"
        permission.display = "Create $entityName"
        permission.entity = entityName
        permission.httpMethod = "POST"
        permission.creatorId = 1
        permission.modifierId = 1
        return permission
    }

    fun readPermission(entityName: String): Permission {
        val permission = Permission()
        permission.version = 0
        permission.authKey = "Read $entityName"
        permission.authUris = "/v[\\d]+/$entityName/[\\d]+"
        permission.display = "Read $entityName"
        permission.entity = entityName
        permission.httpMethod = "GET"
        permission.creatorId = 1
        permission.modifierId = 1
        return permission
    }

    fun updatePermission(entityName: String): Permission {
        val permission = Permission()
        permission.version = 0
        permission.authKey = "Update $entityName"
        permission.authUris = "/v[\\d]+/$entityName/[\\d]+"
        permission.display = "Update $entityName"
        permission.entity = entityName
        permission.httpMethod = "PUT"
        permission.creatorId = 1
        permission.modifierId = 1
        return permission
    }

    fun deletePermission(entityName: String): Permission {
        val permission = Permission()
        permission.version = 0
        permission.authKey = "Delete $entityName"
        permission.authUris = "/v[\\d]+/$entityName/[\\d]+"
        permission.display = "Delete $entityName"
        permission.entity = entityName
        permission.httpMethod = "DELETE"
        permission.creatorId = 1
        permission.modifierId = 1
        return permission
    }

    fun getRolePermissions(roleList: List<Role>, permissionList: List<Permission>): List<RolePermission> {
        val rolePermissionList = mutableListOf<RolePermission>()

        roleList.map { role ->
            permissionList.map { permission ->
                val rolePermission = RolePermission()
                rolePermission.version = 0
                rolePermission.creatorId = 1
                rolePermission.modifierId = 1
                rolePermission.roleId = role.id
                rolePermission.permissionId = permission.id

                rolePermissionList.add(rolePermission)
            }
        }

        rolePermissionList.map {
            it.id = (it.roleId.toString() + it.permissionId.toString()).toLong()
        }
        return rolePermissionList
    }

    fun getRolePermissionRule(rolePermissionList: List<RolePermission>, ruleList: List<Rule>): List<RolePermissionRule> {
        val rolePermissionRuleList = mutableListOf<RolePermissionRule>()
        rolePermissionList.map { rolePermission ->
            ruleList.map { rule ->
                val rolePermissionRule = RolePermissionRule()
                rolePermissionRule.rolePermissionId = rolePermission.id
                rolePermissionRule.ruleId = rule.id
                rolePermissionRuleList.add(rolePermissionRule)
            }
        }

        return rolePermissionRuleList
    }

    fun getPermissionSql(permissionList: List<Permission>): List<String> {
        val sqlList = mutableListOf<String>()
        permissionList.map { permission ->
            val sql = """
                INSERT INTO permission (id, version, auth_key, auth_uris, display, entity, http_method, creator_id, modifier_id) VALUES (${permission.id},${permission.version},'${permission.authKey}','${permission.authUris}','${permission.display}','${permission.entity}','${permission.httpMethod}',${permission.creatorId}, ${permission.modifierId});
                """.trimIndent()

            sqlList.add(sql)
        }
        return sqlList
    }

    fun getRolePermissionSql(rolePermissionList: List<RolePermission>): List<String> {
        val sqlList = mutableListOf<String>()
        rolePermissionList.map { rolePermission ->
            val sql = """
                INSERT INTO role_permission (id, version, creator_id, modifier_id, permission_id, role_id) VALUES (${rolePermission.id}, ${rolePermission.version}, ${rolePermission.creatorId}, ${rolePermission.modifierId}, ${rolePermission.permissionId}, ${rolePermission.roleId});
                """.trimIndent()

            sqlList.add(sql)
        }
        return sqlList
    }

    fun getRolePermissionRuleSql(rolePermissionRuleList: List<RolePermissionRule>): List<String> {
        val sqlList = mutableListOf<String>()
        rolePermissionRuleList.map { rolePermissionRule ->
            val sql = """
                INSERT INTO role_permission_rule (role_permission_id, rule_id) VALUES (${rolePermissionRule.rolePermissionId}, ${rolePermissionRule.ruleId});
                """.trimIndent()

            sqlList.add(sql)
        }
        return sqlList
    }
}