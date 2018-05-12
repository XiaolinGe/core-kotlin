package annotation

import generator.ExcelExporter
import reflection.fieldType

class Person {

    val name: String? = ""

    @ExcelExporter
    @Vb(value = "annotationFirstName", type = fieldType.STRING)
    val firstName: String?  = ""

    @Vb(value = "annotationLastName", type = fieldType.STRING)
    val lastName: String?  = ""
}