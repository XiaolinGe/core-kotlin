package JDBC

import com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil.close
import java.sql.ResultSet
import java.sql.DriverManager
import java.sql.SQLException


object JDBCDemo {
    @Throws(SQLException::class, ClassNotFoundException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        Class.forName("com.mysql.jdbc.Driver")
        val connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/collinson", "root", "root")
        val stmt = connection.createStatement()
        val rs = stmt.executeQuery("SELECT *  FROM role")
        while (rs.next()) {
            val name = rs.getString("A_NAME")
            println("name is:" + name)
        }
        rs.close()
        stmt.close()
        connection.close()
    }
}
