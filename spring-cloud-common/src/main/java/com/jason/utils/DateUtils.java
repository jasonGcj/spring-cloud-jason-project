package com.jason.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateUtils
 * @Description TODO
 * @Author GCJ
 * @Date 2021/3/12 10:42
 */
public class DateUtils {

    public static String getNowDateStr(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
