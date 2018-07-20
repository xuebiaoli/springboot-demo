package com.lxb.example.demo.controller;

import com.lxb.example.demo.models.Article;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("index")
    public String index() {
        Article article = new Article();
        return "index";
    }
}
