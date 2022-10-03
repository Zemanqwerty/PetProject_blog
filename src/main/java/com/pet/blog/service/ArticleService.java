package com.pet.blog.service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.pet.blog.entity.ArticleEntity;
import com.pet.blog.model.ResponseModel;
import com.pet.blog.repository.ArticleRepository;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public ArticleService() {}

    // get all articles
    @Async
    public CompletableFuture<List<ArticleEntity>> getAll() {
        System.out.println(Thread.currentThread().getName());
        return CompletableFuture.completedFuture((List<ArticleEntity>) articleRepository.findAll());
    }

    // crate article
    @Async
    public CompletableFuture<ResponseModel> createArticle(ArticleEntity article) {
        System.out.println(Thread.currentThread().getName());
        articleRepository.save(article);
        return CompletableFuture.completedFuture(ResponseModel.toModel(200, "success", "Article created"));
    }

    // delete article
    @Async
    public CompletableFuture<ResponseModel> deleteArticle(Long id) {
        System.out.println(Thread.currentThread().getName());
        articleRepository.deleteById(id);
        return CompletableFuture.completedFuture(ResponseModel.toModel(200, "success", "article deleted"));
    }

    //get one article by id
    @Async
    public CompletableFuture<Optional<ArticleEntity>> getOne(Long id) {
        System.out.println(Thread.currentThread().getName());
        Optional<ArticleEntity> article = articleRepository.findById(id);
        return CompletableFuture.completedFuture(article);
    }

    //get one article by title
    @Async
    public CompletableFuture<Optional<List<ArticleEntity>>> getAllByTitle(String title) {
        System.out.println(Thread.currentThread().getName());
        Optional<List<ArticleEntity>> article = articleRepository.findAllByTitle(title);
        return CompletableFuture.completedFuture(article);
    }
}
