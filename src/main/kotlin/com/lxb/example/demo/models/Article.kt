package com.lxb.example.demo.models

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.data.elasticsearch.annotations.Document
import java.io.Serializable
import javax.persistence.*
import javax.validation.constraints.NotEmpty


@Document(indexName = "blockdata", type = "s_article", indexStoreType = "fs", shards = 5, replicas = 1, refreshInterval = "-1")
@Table(
        name = "t_article",
        indexes = [
            Index(columnList = "author_id")
        ]
)
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Article(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @org.springframework.data.annotation.Id
        var id: Long? = null,

        @JoinColumn(name = "author_id")
        @ManyToOne()
        var author: User? = null,

        @Column(nullable = false)
        @get: NotEmpty(message = "标题不可为空")
        var title: String? = null,

        @Column(columnDefinition = "TEXT", nullable = false)
        @get: NotEmpty(message = "内容不可为空")
        var content: String? = null
) : Serializable