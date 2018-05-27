package dao

import org.junit.Test
import java.sql.SQLException


class DoTest {
    @Test
    @Throws(SQLException::class)
    fun doTest() {
        val roleDao = RoleDao()
        val role = roleDao.findOne(1)
        println(role)
       // System.out.println("id+title:" + type.getId() + ":" + type.getTitle())
        println("==============================================")

    }

}