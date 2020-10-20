package com.jason.article;

import com.jason.service.RedisCacheService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

/**
 * @ClassName ArticleApplication
 * @Description TODO
 * @Author GCJ
 * @Date 2019/12/2 11:01
 */
@SpringBootApplication
@EnableDiscoveryClient
@ImportResource(locations = {"classpath*:config/mybatis-config.xml"})
public class ArticleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class);
    }

    @Bean
    public RedisCacheService getRedisCacheService(){
        return new RedisCacheService();
    }

}
