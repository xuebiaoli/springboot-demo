package com.lxb.example.demo.models

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.util.*
import javax.persistence.*
import javax.validation.constraints.*

@Table(
        name = "t_user",
        uniqueConstraints = [
            UniqueConstraint(columnNames = ["username", "email", "phone"])
        ],
        indexes = [
            Index(columnList = "username", unique = true),
            Index(columnList = "email", unique = true),
            Index(columnList = "phone", unique = true)
        ]
)
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,

        @Column(unique = true, nullable = false)
        @get: Size(min = 6, max = 18, message = "用户名长度6~18")
        @get: NotEmpty(message = "用户名不可为空")
        var username: String? = null,

        @Column
        var nickname: String? = null,

        @Column
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        var password: String? = null,

        @Column(unique = true)
        @get: Email
        var email: String? = null,

        @Column(unique = true)
        var phone: String? = null,

        @Column
        var avatar: String? = null,

        @Column(name = "create_time")
        var createTime: Date? = null,

        @Column(name = "update_time")
        var updateTime: Date? = Date(),

        @Column
        var enabled: Boolean? = true,

        @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
        var articles: List<Article>? = null
) : Serializable