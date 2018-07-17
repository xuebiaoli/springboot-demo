package com.lxb.example.demo.models

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.*
import javax.validation.constraints.*

@Table(name = "t_user")
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long?,

        @Column
        @get: Size(min=6, max=18, message = "用户名长度6~18")
        @get: NotEmpty(message = "用户名不可为空")
        var username: String?,

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