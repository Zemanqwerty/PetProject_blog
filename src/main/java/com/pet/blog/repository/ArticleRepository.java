package com.pet.blog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pet.blog.entity.ArticleEntity;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, Long> {
    
}
