package com.jason.domain;

import com.jason.user.IUser;

import java.io.Serializable;

/**
 * @ClassName UserEntity
 * @Description TODO
 * @Author GCJ
 * @Date 2020/9/17 15:00
 */
public class UserEntity implements Serializable,IUser {
    private String userId;

    private String userAccount;

    private String userName;

    private String userPhone;

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getUserAccount() {
        return userAccount;
    }

    @Override
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getUserPhone() {
        return userPhone;
    }

    @Override
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
