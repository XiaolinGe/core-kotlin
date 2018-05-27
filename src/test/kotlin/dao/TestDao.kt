package dao

import org.junit.Test
import java.sql.SQLException


class DoTest {
    @Test
    @Throws(SQLException::class)
    fun doTest() {
        val roleDao = RoleDao()
        val role = roleDao.findOne(1)
        val roleList = roleDao.findAll()
        val deletedRole = roleDao.deleteOne(106)
        println(role)
        println(roleList)
        println(deletedRole)
       // System.out.println("id+title:" + type.getId() + ":" + type.getTitle())
        println("==============================================")

    }

}