package com.jason.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @ClassName ZuulApplication
 * @Description TODO
 * @Author GCJ
 * @Date 2019/11/11 16:06
 */
@EnableZuulProxy
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//排除自动配置
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class);
    }
}
