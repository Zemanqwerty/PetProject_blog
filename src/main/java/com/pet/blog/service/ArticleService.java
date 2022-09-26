package com.pet.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.blog.entity.ArticleEntity;
import com.pet.blog.model.ResponseModel;
import com.pet.blog.repository.ArticleRepository;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    public ResponseModel createArticle(ArticleEntity article) {
        articleRepository.save(article);
        return ResponseModel.toModel(200, "success", "Article created");
    }
}
