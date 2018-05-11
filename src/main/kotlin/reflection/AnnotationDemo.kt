package reflection

@MyAnnotation1("this isannotation1")
class AnnotationDemo {

    @MyAnnotation2(description = "this is annotation2", isAnnotation = true)
    fun sayHello() {

        println("hello world!")

    }
}

fun main(args: Array<String>) {
    val a = AnnotationDemo()
    val s = a.sayHello()
    println(s)
}