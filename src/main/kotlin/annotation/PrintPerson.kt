package annotation

import java.util.*


fun main(args: Array<String>) {
    val fields = Person::class.java.declaredFields
    fields.forEach {
        it.declaredAnnotations.forEach { anno: Annotation? ->
            when(anno){
                is Vb ->{
                    val value = anno.value
                    val type = anno.type
                    println("value: $value")
                    println("type: $type")
                }
            }
        }
        //val annotation = it.getDeclaredAnnotation(Vb::class.java)


    }

}