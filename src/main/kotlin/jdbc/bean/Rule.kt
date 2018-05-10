package jdbc.bean

data class Rule(

        var id: Long? = null,

        var version: Long? = null,

        var name: String? = null,

        var params: String? = null,

        var type: String? = null,

        var enable: String? = null
)