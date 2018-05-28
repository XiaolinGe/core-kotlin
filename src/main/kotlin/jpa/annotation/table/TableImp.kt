package jpa.annotation.table

fun getTableName(clazz: Class<*>):String {
    var tableName = clazz.getDeclaredAnnotation(Table::class.java).name
    if(tableName.isBlank()) tableName = clazz.name.substringAfterLast(".")
    return tableName
}
