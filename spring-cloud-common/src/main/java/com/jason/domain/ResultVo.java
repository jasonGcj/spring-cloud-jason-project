package com.jason.domain;

import java.io.Serializable;

/**
 * @ClassName ResultVo
 * @Description TODO
 * @Author GCJ
 * @Date 2019/11/11 9:33
 */
public class ResultVo implements Serializable {

    private String message;

    private int total;

    private int code;

    private Object data;

    private boolean ok;

    public ResultVo(boolean ok,int code,String message){
        this.ok = ok;
        this.code = code;
        this.message = message;

    }

    public ResultVo(boolean ok,int code,String message,Object data){
        this.ok = ok;
        this.code = code;
        this.message = message;
        this.total = total;
        this.data = data;
    }

    public ResultVo(boolean ok,int code,String message,int total,Object data){
        this.ok = ok;
        this.code = code;
        this.message = message;
        this.total = total;
        this.data = data;
    }

    public ResultVo(){

    }

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
