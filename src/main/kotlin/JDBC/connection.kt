package JDBC

import JDBC.bean.Role
import JDBC.bean.Rule
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

    @JvmStatic fun main(args: Array<String>) {
        // make a connection to MySQL Server
        getConnection()
        // execute the query via connection object
        executeMySQLQuery()
    }
    fun executeMySQLQuery() {
        var stmt: Statement? = null
        var resultset: ResultSet? = null

        try {
            val roleList = getRoles(getResultSet("role"))
            val ruleList = getRules(getResultSet("rule"))

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
    fun getConnection(){
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

    fun getResultSet(beanName: String): ResultSet{
        var stmt: Statement? = null
        var resultset: ResultSet? = null
        stmt = conn!!.createStatement()
        val sql = "SELECT * FROM collinson.$beanName;"
        resultset = stmt!!.executeQuery(sql)

        if (stmt.execute(sql)) {
            resultset = stmt.resultSet

        }
        return resultset
    }

    fun getRoles(resultset: ResultSet): List<Role> {
        val roleList = mutableListOf<Role>()
        while (resultset.next()) {
            val role = JDBC.bean.Role()
            role.id = resultset.getLong("id")
            role.version = resultset.getLong("version")
            role.name = resultset.getString("name")
            role.creatorId = resultset.getLong("creator_id")
            role.modifierId = resultset.getLong("modifier_id")
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
}