package com.jason.enums;

/**
 * @ClassName LikedStatusEnum
 * @Description TODO
 * @Author GCJ
 * @Date 2020/10/14 12:21
 */
public enum LikedStatusEnum {

    LIKE("1", "点赞"),
    UNLIKE("0", "取消点赞/未点赞"),
    COLLECT("1", "收藏"),
    UNCOLLECT("0", "取消收藏/未收藏");

    private String code;

    private String msg;

    LikedStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
