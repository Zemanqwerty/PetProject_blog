package com.pet.blog.model;

import java.util.Date;

import com.pet.blog.entity.ArticleEntity;

public class ArticleModel {
    private Long id;
    private String title;
    private String text;
    private Date createdAt;

    public static ArticleModel toModel(ArticleEntity entity) {
        ArticleModel model = new ArticleModel();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setText(entity.getText());
        model.setCreatedDate(entity.getCreatedDate());
        return model;
    }

    public ArticleModel() {};

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedDate(Date date) {
        this.createdAt = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }
}
