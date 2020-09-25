package com.jason.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName ArticleApplication
 * @Description TODO
 * @Author GCJ
 * @Date 2019/12/2 11:01
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ArticleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class);
    }
}
