package jpa.bean

import jpa.annotation.id.Id


class Role {

    @Id
    var id: Long? = null

    var version: Long? = null

    var name: String? = null

    var creatorId: Long? = null

    var modifierId: Long? = null

}