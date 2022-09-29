package com.pet.blog.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pet.blog.entity.ArticleEntity;
import com.pet.blog.repository.ArticleRepository;
import com.pet.blog.service.ArticleService;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/get_all")
    public CompletableFuture getAll() {
        try {
            return CompletableFuture.completedFuture(articleService.getAll());
        } catch (Exception e) {
            return CompletableFuture.completedFuture("server got a troubles: " + e);
        }
    }

    @GetMapping("/get_one")
    public CompletableFuture getOne(@RequestParam Long id) {
        try {
            return CompletableFuture.completedFuture(articleService.getOne(id));
        } catch (Exception e) {
            return CompletableFuture.completedFuture("server got a troubles: " + e);
        }
    }

    @GetMapping("/get_by_title")
    public CompletableFuture getByTitle(@RequestParam String title) {
        try {
            return CompletableFuture.completedFuture(articleService.getAllByTitle(title));
        } catch (Exception e) {
            return CompletableFuture.completedFuture("server got a troubles: " + e);
        }
    }

    @PostMapping("/create")
    public CompletableFuture create(@RequestBody ArticleEntity article) {
        try {
            return CompletableFuture.completedFuture(articleService.createArticle(article));
        } catch (Exception e) {
            return CompletableFuture.completedFuture("server got a troubles: " + e);
        }
    }

    @GetMapping("/delete")
    public CompletableFuture delete(@RequestParam Long id) {
        try {
            return CompletableFuture.completedFuture(articleService.deleteArticle(id));
        } catch (Exception e) {
            return CompletableFuture.completedFuture("server got a troubles: " + e);
        }
    }
}
