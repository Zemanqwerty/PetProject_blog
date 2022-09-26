package com.pet.blog.repository;

import org.springframework.data.repository.CrudRepository;

import com.pet.blog.entity.ArticleEntity;

public interface ArticleRepository extends CrudRepository<ArticleEntity, Long> {
    
}
