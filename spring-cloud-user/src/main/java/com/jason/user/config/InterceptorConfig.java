package com.jason.user.config;

import com.jason.user.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName InterceptorConfig
 * @Description TODO
 * @Author GCJ
 * @Date 2019/11/13 9:35
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        List<String> list = new ArrayList<>();
        list.add("/**/register/**");
        list.add("/**/login/**");
        list.add("/**/phoneLogin/**");
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**") //拦截的地址
                .excludePathPatterns(list);//放行的地址
    }
}
