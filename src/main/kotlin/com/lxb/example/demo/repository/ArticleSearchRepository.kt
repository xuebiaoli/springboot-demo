package com.lxb.example.demo.repository

import com.lxb.example.demo.models.Article
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface ArticleSearchRepository : ElasticsearchRepository<Article, Long> {
}