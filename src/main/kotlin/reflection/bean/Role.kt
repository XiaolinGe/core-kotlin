package reflection.bean

class Role constructor(
        var name: String? = null
) {

    fun test(str:String): String? {
        return "test role fun: $str"
    }

}