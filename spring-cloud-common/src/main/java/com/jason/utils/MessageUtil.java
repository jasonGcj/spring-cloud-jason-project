package com.jason.utils;

import com.google.gson.JsonObject;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName MessageUtil
 * @Description TODO
 * @Author GCJ
 * @Date 2019/11/19 9:19
 */
public class MessageUtil {
    public static void jhson() {
        String jsonString ="{\"name\":\"zhangsan\",\"password\":\"zhangsan123\",\"email\":\"10371443@qq.com\"}";
        JsonObject json =  new JsonObject();
    }
    public static String getNowDateStr() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(str.getBytes("utf-8"));
            System.out.println(toHex(bytes).toLowerCase());
            return toHex(bytes).toLowerCase();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }
}
