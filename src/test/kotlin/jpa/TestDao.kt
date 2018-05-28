package jpa

import jpa.dao.RoleDao
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
        val insertRole = roleDao.saveOne(null)
        val updatedRole = roleDao.saveOne(107)

        println(role)
        println(roleList)
        println(deletedRole)
        println(insertRole)
        println(updatedRole)
        println("==============================================")

    }

}