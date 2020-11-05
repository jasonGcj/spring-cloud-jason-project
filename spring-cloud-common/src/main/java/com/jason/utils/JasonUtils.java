package com.jason.utils;

import java.util.Random;

/**
 * @ClassName JasonUtils
 * @Description TODO
 * @Author GCJ
 * @Date 2020/11/2 15:04
 */
public class JasonUtils {

    public static String randomNumber(int length) {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

}
