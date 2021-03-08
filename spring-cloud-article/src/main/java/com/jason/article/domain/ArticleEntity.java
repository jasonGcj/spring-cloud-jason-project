package com.jason.article.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ArticelDto
 * @Description TODO
 * @Author GCJ
 * @Date 2019/12/2 11:22
 */
public class ArticleEntity implements Serializable {
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
    private int pageviews;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建的用户
     */
    private Date createUserAcount;
    /**
     * 点赞数量
     */
    private int likedCount;
    /**
     * 点赞数量
     */
    private int collctCount;
    /**
     * 浏览数量
     */
    private int browseCount;
    /**
     * 作者是否点赞
     */
    private boolean isLiked;
    /**
     * 作者是否收藏
     */
    private boolean isCollect;

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

    public int getPageviews() {
        return pageviews;
    }

    public void setPageviews(int pageviews) {
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

    public int getLikedCount() {
        return likedCount;
    }

    public void setLikedCount(int likedCount) {
        this.likedCount = likedCount;
    }

    public int getCollctCount() {
        return collctCount;
    }

    public void setCollctCount(int collctCount) {
        this.collctCount = collctCount;
    }

    public int getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(int browseCount) {
        this.browseCount = browseCount;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }
}
