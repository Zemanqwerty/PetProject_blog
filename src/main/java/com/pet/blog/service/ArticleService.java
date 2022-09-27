package com.pet.blog.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ArticleEntity> getAll() {
        return (List<ArticleEntity>) articleRepository.findAll();
    }

    // crate article
    public ResponseModel createArticle(ArticleEntity article) {
        articleRepository.save(article);
        return ResponseModel.toModel(200, "success", "Article created");
    }
}
