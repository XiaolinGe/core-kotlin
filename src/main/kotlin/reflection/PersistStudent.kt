package reflection



object PersistStudent {

    @JvmStatic
    fun main(args: Array<String>) {

        val c = Class.forName("reflection.jpa.bean.Person").newInstance()


        val methodArray = c.javaClass.declaredMethods

        for (i in methodArray.indices) {

            if (methodArray[i].isAnnotationPresent(ValueBind::class.java)) {

                val annotation = methodArray[i]

                        .getAnnotation(ValueBind::class.java)

                val type = annotation.type.toString()

                val value = annotation.value

                println("type: $type")
                println("value: $value")

            }

        }


    }

}