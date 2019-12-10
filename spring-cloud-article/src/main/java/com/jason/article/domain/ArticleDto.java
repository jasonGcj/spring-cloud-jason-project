package com.jason.article.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ArticelDto
 * @Description TODO
 * @Author GCJ
 * @Date 2019/12/2 11:22
 */
public class ArticleDto implements Serializable {
    /*主建*/
    private String id;

    /*文章id*/
    private String articleId;

    /*标题*/
    private String title;

    /*文章简介*/
    private String summary;

    /*是否置顶*/
    private int isTop;

    /*文章访问量*/
    private int traffic;

    /*创建时间*/
    private Date createTime;

    /*修改时间*/
    private Date modifyTime;

    /*文章内容*/
    private String context;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getIsTop() {
        return isTop;
    }

    public void setIsTop(int isTop) {
        this.isTop = isTop;
    }

    public int getTraffic() {
        return traffic;
    }

    public void setTraffic(int traffic) {
        this.traffic = traffic;
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
