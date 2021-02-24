package com.jason.test;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

/**
 * @ClassName TestDemo
 * @Description TODO
 * @Author GCJ
 * @Date 2020/10/26 16:51
 */
@SpringBootTest
public class TestDemo {
    @Test
    public void test1(){
        String value = "[A&460010712788533&864948040260627&06,2022,0&A6]";
        String value1 = "460010712788533&864948040260627&06,2022,0";

        Integer checkValue = Integer.parseInt(value.substring(value.length() - 3, value.length() - 1), 16);

        Integer checkValue1 = Integer.parseInt(value.substring(value.length() - 3, value.length() - 1), 16);
        System.out.println("checkValue = " + checkValue);
        System.out.println("checkValue1 = " + checkValue1);

        String data = value.substring(1, value.length() - 3);
        System.out.println("data = " + data);

        char[] chars = data.toCharArray();
        int sum = 0;

        for (int i = 0; i < chars.length; i++) {
            sum += chars[i];
        }
        String s = Integer.toHexString(sum);
        Integer integer = Integer.valueOf(s,16);
        System.out.println(integer % 256);
    }

    @Test
    public void  test2(){
        String string = "653846001071278853338864948040260627380644202244038";

        int total = 0;
       /* total += Integer.valueOf("06",16);
        total += Integer.valueOf("2022",16);
        total += Integer.valueOf("0",16);*/
        for (int i = 0; i <string.length() ; i++) {
            total += Integer.valueOf(string.substring(i,i+1),16);
        }
        System.out.println(total & 0xff);
        int mod = total % 256;
        String hex = Integer.toHexString(mod);
        int len = hex.length();
        // 如果不够校验位的长度，补0,这里用的是两位校验
        if (len < 2) {
            hex = "0" + hex;
        }
        System.out.println(hex);

       /* String s1 = "R&460010712788533&864948040260627&06,2022&";
        System.out.println(s1.length());*/
    }

    @Test
    public void  testMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("qyp","乔一鹏");
        map.put("zry","张瑞云");
        System.out.println(map.get("qyp"));



    }

}
