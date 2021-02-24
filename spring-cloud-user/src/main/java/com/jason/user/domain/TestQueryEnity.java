package com.jason.user.domain;

import java.io.Serializable;

/**
 * @ClassName TestQueryDto
 * @Description TODO
 * @Author GCJ
 * @Date 2021/2/24 20:47
 */
public class TestQueryEnity implements Serializable {

    private String id;

    private String aid;

    private String bids;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getBids() {
        return bids;
    }

    public void setBids(String bids) {
        this.bids = bids;
    }
}
