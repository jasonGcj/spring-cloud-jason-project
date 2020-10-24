package com.jason.article;

import com.jason.service.RedisCacheService;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

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

    @Bean
    public RedisCacheService getRedisCacheService(){
        return new RedisCacheService();
    }

    @Bean
    public IRule iRule(){
//        return new RandomRule();//随机策略
        return new RoundRobinRule();//轮询策略
    }

}
