package jpa.annotation.id

import java.lang.reflect.Field


fun getId(clazz: Class<*>): Field? {
    return clazz.declaredFields.firstOrNull { field ->
        field.declaredAnnotations.any { annotation ->
            annotation is Id
        }
    }
}