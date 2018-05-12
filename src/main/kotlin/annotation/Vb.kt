package annotation

import reflection.fieldType
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Vb(val value: String, val type: fieldType)

