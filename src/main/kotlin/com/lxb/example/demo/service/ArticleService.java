package com.lxb.example.demo.service;

import com.lxb.example.demo.models.Article;
import com.lxb.example.demo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    /*
     * Java变量的初始化顺序为：静态变量或静态语句块–>实例变量或初始化语句块–>构造方法–>@Autowired
     * */
    private ArticleRepository articleRepository;

    @Autowired
    private void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    public Article findById(Long userId) {
        return articleRepository.findById(userId).orElse(null);
    }
}
