package com.jason.article.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ArticelDto
 * @Description TODO
 * @Author GCJ
 * @Date 2019/12/2 11:22
 */
public class ArticleDto implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 图片
     */
    private String postMediumImage;

    /**
     * 文章链接
     */
    private String link;

    /**
     * 作者
     */
    private String author;

    /**
     * 介绍
     */
    private String excerpt;

    /**
     * 喜欢的数量
     */
    private Integer likeCount;

    /**
     * 小标题
     */
    private String categoryName;

    /**
     * 发布日期
     */
    private Date postDate;

    /**
     * 观看次数
     */
    private Integer pageviews;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建的用户
     */
    private Date createUserAcount;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostMediumImage() {
        return postMediumImage;
    }

    public void setPostMediumImage(String postMediumImage) {
        this.postMediumImage = postMediumImage;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Integer getPageviews() {
        return pageviews;
    }

    public void setPageviews(Integer pageviews) {
        this.pageviews = pageviews;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateUserAcount() {
        return createUserAcount;
    }

    public void setCreateUserAcount(Date createUserAcount) {
        this.createUserAcount = createUserAcount;
    }
}
