package com.jason.domain;

import java.io.Serializable;

/**
 * @ClassName CommonEntity
 * @Description TODO
 * @Author GCJ
 * @Date 2020/10/29 10:44
 */
public class CommonEntity implements Serializable {
    private int start;

    private int limit;

    private String keywords;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
