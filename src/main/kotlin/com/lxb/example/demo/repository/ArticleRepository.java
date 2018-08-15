package com.lxb.example.demo.repository;

import com.lxb.example.demo.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {
//    Article findUserByUsername(String username);
//    void deleteUserByUsername(String username);
}