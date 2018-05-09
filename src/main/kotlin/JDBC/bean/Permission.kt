package JDBC.bean

data class Permission(

        val id: Long? = null,

        val version: Long? = null,

        var authKey: String? = null,

        var authUris: String? = null,

        var display: String? = null,

        val entity: String? = null,

        var httpMethod: String? = null,

        var icon: String? = null,

        var menuUrl: String? = null,

        var creatorId: Long? = null,

        val modifierId: Long? = null

)