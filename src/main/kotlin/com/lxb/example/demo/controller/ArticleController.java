package com.lxb.example.demo.controller;

import com.lxb.example.demo.models.Article;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @GetMapping
    public List<Article> index() {
        return new ArrayList<>(4);
    }
}
