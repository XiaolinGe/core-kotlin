package generator

import annotation.Person

fun main(args: Array<String>) {
    // class -> List<CodeFiled>
    // CodeField name -> filed name
    // CodeField exporter -> annotation has: true, annotation not has: false

    val fields = Person::class.java.declaredFields

    val p = getEntity(Person::class.java)
    println(p)

}


fun getList(clazz: Class<*>): List<CodeField>{

    return clazz.declaredFields.map {
        CodeField(
                name = it.name,
                exporetable = it.isAnnotationPresent(ExcelExporter::class.java)
        )
    }

}


fun getEntity(clazz: Class<*>): CodeEntity {
        return CodeEntity(
                name = clazz.simpleName,
                fields = getList(clazz)

        )
}

// val list : List<Any> = JdbcUtils.getBean("select * from foo",Foo::class.java)

// val list : List<Any> = JdbcUtils.getBean(Foo::class.java)

// JdbcUtils.saveBean(foo)
//foo.class.fields
//insert foo (f1,f2,f3) values (v1,v2,v3)