package reflection.bean

import reflection.ValueBind
import reflection.fieldType


 class Student {
    private var name: String? = null
    private var age = 0
    private var studentId = 1


    fun getName(): String? {
        return name
    }

    @ValueBind(type = fieldType.STRING, value = "aa")
    fun setName(name: String) {
        this.name = name
    }

    fun getAge(): Int? {
        return age
    }

    fun getStudentId(): Int? {
        return studentId
    }

    @ValueBind(type = fieldType.STRING, value = "101")
    fun setStudentId(studentId: Int) {
        this.studentId = studentId

    }





}