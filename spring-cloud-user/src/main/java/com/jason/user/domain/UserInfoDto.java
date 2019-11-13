package com.jason.user.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName UserInfoDto
 * @Description TODO
 * @Author GCJ
 * @Date 2019/11/11 9:36
 */
public class UserInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String userName;

    private String passWord;

    private String phoneNumber;

    private Date createTime;

    private Date modifyTime;

    private String active;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
