package com.example.demo.controller;

import com.example.demo.dao.Article;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {

    private final SessionFactory sessionFactory;

    public HelloWorldController(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @GetMapping
    public List<Article> articles() {
        var session = sessionFactory.getCurrentSession();
        return session.createQuery("from Article", Article.class).list();

    }

    @Transactional
    @PostMapping("/article/create")
    public void addArticle() {
        var session = sessionFactory.getCurrentSession();
        var article = new Article();
        article.setName("Article Title Example!");
        session.save(article);
    }

}
