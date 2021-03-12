package com.jason.user.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName RedPackageDto
 * @Description TODO
 * @Author GCJ
 * @Date 2021/3/12 10:11
 */
public class RedPackageDto implements Serializable {
    /**
     * 红包id
     */
    private String id;
    /**
     * 红包数量
     */
    private int rpCount;
    /**
     * 红包总金额
     */
    private BigDecimal rpMoney;
    /**
     *红包备注
     */
    private String rpRemarks;
    /**
     *红包创建时间
     */
    private String date;
    /**
     *发送人账号
     */
    private String account;
    /**
     *红包类型
     */
    private String rpType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRpCount() {
        return rpCount;
    }

    public void setRpCount(int rpCount) {
        this.rpCount = rpCount;
    }

    public BigDecimal getRpMoney() {
        return rpMoney;
    }

    public void setRpMoney(BigDecimal rpMoney) {
        this.rpMoney = rpMoney;
    }

    public String getRpRemarks() {
        return rpRemarks;
    }

    public void setRpRemarks(String rpRemarks) {
        this.rpRemarks = rpRemarks;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRpType() {
        return rpType;
    }

    public void setRpType(String rpType) {
        this.rpType = rpType;
    }
}
