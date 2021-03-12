package com.jason.utils;

import java.util.UUID;

/**
 * @ClassName UuidUtils
 * @Description TODO
 * @Author GCJ
 * @Date 2020/9/4 14:53
 */
public class UUidUtils {
    public static String getUUid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
