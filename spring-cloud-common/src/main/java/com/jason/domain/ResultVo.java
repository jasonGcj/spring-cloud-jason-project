package com.jason.domain;

/**
 * @ClassName ResultVo
 * @Description TODO
 * @Author GCJ
 * @Date 2019/11/11 9:33
 */
public class ResultVo {

    private String message;

    private int total;

    private int code;

    private Object data;

    private boolean ok;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
