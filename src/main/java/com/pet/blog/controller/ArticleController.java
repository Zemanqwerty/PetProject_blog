package com.pet.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity getAll() {
        try{
            return ResponseEntity.ok().body(articleRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody ArticleEntity article) {
        try {
            return ResponseEntity.ok().body(articleService.createArticle(article));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
