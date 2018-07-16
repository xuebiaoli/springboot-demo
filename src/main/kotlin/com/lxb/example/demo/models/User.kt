package com.lxb.example.demo.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email

@Table(name = "t_user")
@Entity
data class User(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long?,
        @Column var username: String?,
        @Column var nickname: String?,
        @Column @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) var password: String?,
        @Column @Email var email: String?,
        @Column var phone: String?,
        @Column var avatar: String?,
        @Column(name = "create_time") var createTime: Date?,
        @Column(name = "update_time") var updateTime: Date?,
        @Column var enabled: Boolean?
) {
    constructor() : this(null, null, null, null, null, null, null, null, null, null)
}