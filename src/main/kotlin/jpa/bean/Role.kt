package jpa.bean

import jpa.annotation.id.Id
import jpa.annotation.primaryKey.PrimaryKey
import jpa.annotation.table.Table

@Table(name = "aci_role")
class Role {

    @Id
    @PrimaryKey
    var id: Long? = null

    var version: Long? = null

    var name: String? = null

    var creatorId: Long? = null

    var modifierId: Long? = null

}