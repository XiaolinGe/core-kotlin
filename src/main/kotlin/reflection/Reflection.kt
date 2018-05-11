package reflection

import reflection.bean.Role
import java.lang.reflect.Method




fun main(args: Array<String>) {
    val permissionClass = Class.forName("reflection.bean.Permission")
    val roleClass = Class.forName("reflection.bean.Role")
    getModifiers(permissionClass)
    getSuperClassName(permissionClass)
    createPermissionObj(permissionClass)
    createRoleObj(roleClass)
    doMethod()
    doTestMethod()
    doField()

}

fun getModifiers(c: Class<*>) {
    val mod = c.getModifiers()
    println("Modifiers:  $mod ")
}

fun getSuperClassName(c: Class<*>) {
    val superClass = c.superclass
    val superClassName = c.name
    println("superClassName:  $superClassName ")
}

fun createPermissionObj(c: Class<*>) {
    val permission = c.newInstance()

    val method: Method = c.getDeclaredMethod("test")
    method.invoke(permission)
    println("method: $method")

}

fun createRoleObj(c: Class<*>) {
    val classType = Role::class.java
    val con = classType.getConstructor(String::class.java)
    val role = con.newInstance( "RoleName")
    println("role: $role")
    println("role name: ${role.name}")

}

fun doMethod() {
    val classType = Role::class.java
    val con = classType.getConstructor(String::class.java)
    val role = con.newInstance( "RoleName")
    val method = classType.getMethod("setName", String::class.java)
    method.invoke(role, "invokeRoleName")

    println("invoke : ${role.name} ")
}

fun doTestMethod () {
    val classType = Role::class.java
    val con = classType.getConstructor(String::class.java)
    val role = con.newInstance("roleConstructName")

    val method = classType.getMethod("test", String::class.java)
    val test = method.invoke(role, "invoke")
    println("invoke :  $test")

}

fun doField() {
    val role = Role("roleName")
    val classType = Role::class.java
    val field = classType.getDeclaredField("name")
    field.isAccessible = true
    field.set(role, "fieldName")
    field.isAccessible = false
    println("role name: ${role.name}")
    println("field type: ${field.genericType}" )

}

















