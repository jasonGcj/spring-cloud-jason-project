package com.jason.article.domain;

import java.io.Serializable;

/**
 * @ClassName HotImageEntity
 * @Description TODO
 * @Author GCJ
 * @Date 2020/10/29 14:17
 */
public class HotImageEntity implements Serializable {

    private String id;
    private String type;
    private String post_title;
    private String post_medium_image;
    private String articleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_medium_image() {
        return post_medium_image;
    }

    public void setPost_medium_image(String post_medium_image) {
        this.post_medium_image = post_medium_image;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
