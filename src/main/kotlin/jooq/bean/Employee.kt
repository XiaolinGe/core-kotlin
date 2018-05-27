package jooq.bean

class Employee {

    var id: Long? = null

    var name: String? = "employee"

    var gender: String? = "male"

    fun setTheName(name: String) {
        this.name = name
    }

}