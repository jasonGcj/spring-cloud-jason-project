package com.jason.user.domain;

import java.io.Serializable;

/**
 * @ClassName UserInfoEntity
 * @Description TODO
 * @Author GCJ
 * @Date 2020/10/9 14:30
 */
public class UserInfoEntity implements Serializable {
    private String username;

    private String token;

    private long account;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getAccount() {
        return account;
    }

    public void setAccount(long account) {
        this.account = account;
    }
}
