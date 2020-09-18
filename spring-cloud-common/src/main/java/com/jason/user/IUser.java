package com.jason.user;

/**
 * @ClassName IUuser
 * @Description TODO
 * @Author GCJ
 * @Date 2020/9/17 14:58
 */
public interface IUser {
    void setUserId(String id);

    String getUserId();

    void setUserAccount(String userAccount);

    String getUserAccount();

    void setUserName(String userNmae);

    String getUserName();

    void setUserPhone(String userPhone);

    String getUserPhone();
}
