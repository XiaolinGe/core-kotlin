package jpa.annotation.primaryKey

import java.lang.reflect.Field

fun getPrimaryKey(clazz: Class<*>): Field? {
    return clazz.declaredFields
            .firstOrNull {field ->
                field.declaredAnnotations.any { annotation ->
                    annotation is PrimaryKey
                }
            }
}