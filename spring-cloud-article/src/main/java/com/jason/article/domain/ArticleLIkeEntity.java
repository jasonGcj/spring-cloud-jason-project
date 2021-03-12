package com.jason.article.domain;

import java.io.Serializable;

/**
 * @ClassName ArticleLIkeEntity
 * @Description TODO
 * @Author GCJ
 * @Date 2021/3/10 15:30
 */
public class ArticleLIkeEntity implements Serializable {
    /**
     * 点赞数量
     */
    private int likedCount;
    /**
     * 收藏数量
     */
    private int collectCount;
    /**
     * 是否点赞
     */
    private int isLiked;
    /**
     * 是否收藏
     */
    private int isCollect;

    public int getLikedCount() {
        return likedCount;
    }

    public void setLikedCount(int likedCount) {
        this.likedCount = likedCount;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public int getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(int isLiked) {
        this.isLiked = isLiked;
    }

    public int getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(int isCollect) {
        this.isCollect = isCollect;
    }
}
