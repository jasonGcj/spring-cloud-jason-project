package com.jason.article.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName MyFollowDto
 * @Description TODO
 * @Author GCJ
 * @Date 2020/9/4 14:36
 */
public class MyFollowEntity implements Serializable {
    private String id;

    /**
     * 首页图片
     */
    private String categoryThumbnailImage;

    /**
     * 图片对应的名字
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 订阅、未订阅
     */
    private String subimg;

    /**
     * 记录
     */
    private Integer count;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 超连接
     */
    private String links;
    /**
     * 创建用户
     */
    private String createUserAcount;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 链接
     */
    private String link;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryThumbnailImage() {
        return categoryThumbnailImage;
    }

    public void setCategoryThumbnailImage(String categoryThumbnailImage) {
        this.categoryThumbnailImage = categoryThumbnailImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubimg() {
        return subimg;
    }

    public void setSubimg(String subimg) {
        this.subimg = subimg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getCreateUserAcount() {
        return createUserAcount;
    }

    public void setCreateUserAcount(String createUserAcount) {
        this.createUserAcount = createUserAcount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
