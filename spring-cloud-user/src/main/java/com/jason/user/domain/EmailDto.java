package com.jason.user.domain;

import java.io.Serializable;

/**
 * @ClassName EmailDto
 * @Description TODO
 * @Author GCJ
 * @Date 2020/11/2 14:37
 */
public class EmailDto implements Serializable {
    /**
     * email 邮箱
     */
    private String emailAddress;
    /**
     * email 验证码
     */
    private String emailCode;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode;
    }
}
