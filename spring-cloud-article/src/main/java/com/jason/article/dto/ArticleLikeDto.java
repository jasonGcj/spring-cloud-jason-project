package com.jason.article.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ArticleLikeDto
 * @Description TODO
 * @Author GCJ
 * @Date 2020/10/14 10:33
 */
public class ArticleLikeDto implements Serializable {
    /**
     * 文章id
     */
    private String articleId;
    /**
     * 点赞的用户
     */
    private String username;
    /**
     * 点赞的用户id
     */
    private String account;
    /**
     * 点赞时间
     */
    private Date createTime;
    /**
     * 操作类型 1 点赞 2我的喜欢
     */
    private int opeType;
    /**
     * 点赞状态
     */
    private String likeStatus;

    public String getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(String likeStatus) {
        this.likeStatus = likeStatus;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getOpeType() {
        return opeType;
    }

    public void setOpeType(int opeType) {
        this.opeType = opeType;
    }
}
