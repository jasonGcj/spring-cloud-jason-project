package com.jason.test;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
        long startTime = System.nanoTime();        //开始時間
        System.out.println(
                startTime
        );
    }
}
