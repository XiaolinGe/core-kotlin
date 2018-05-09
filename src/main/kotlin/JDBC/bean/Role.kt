package JDBC.bean

data class Role(

        val id: Long? = null,

        val version: Long? = null,

        val name: String? = null,

        var creatorId: Long? = null,

        val modifierId: Long? = null

)