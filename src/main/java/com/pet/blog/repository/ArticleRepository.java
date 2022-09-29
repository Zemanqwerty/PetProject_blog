package com.pet.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pet.blog.entity.ArticleEntity;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, Long> {
    Optional<List<ArticleEntity>> findAllByTitle(String title);
}
