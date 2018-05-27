package jdbc

import java.sql.DriverManager
import java.sql.SQLException


object JDBCDemo {
    @Throws(SQLException::class, ClassNotFoundException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        Class.forName("com.mysql.jdbc.Driver")
        val connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/collinson", "root", "root")
        val stmt = connection.createStatement()
        val rs = stmt.executeQuery("SELECT *  FROM aci_role")
        while (rs.next()) {
            val name = rs.getString("NAME")
            println("name is:" + name)
        }
        rs.close()
        stmt.close()
        connection.close()
    }
}
