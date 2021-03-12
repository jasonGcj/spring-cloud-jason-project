package com.jason.user.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName GetRedPackage
 * @Description TODO
 * @Author GCJ
 * @Date 2021/3/12 11:06
 */
public class GetRedPackage implements Serializable {
    /**
     * 红包id
     */
    private String rpId;
    /**
     * 账号信息
     */
    private String account;
    /**
     * 红包的发布者
     */
    private String rpAccount;

    /**
     * 所抢金额
     */
    private BigDecimal money;

    /**
     * 时间
     */
    private String  date;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRpAccount() {
        return rpAccount;
    }

    public void setRpAccount(String rpAccount) {
        this.rpAccount = rpAccount;
    }

    public String getRpId() {
        return rpId;
    }

    public void setRpId(String rpId) {
        this.rpId = rpId;
    }
}
