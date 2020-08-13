package com.jason.user.interceptor;

import com.jason.utils.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName JwtInterceptor
 * @Description TODO
 * @Author GCJ
 * @Date 2019/11/13 9:24
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtInterceptor.class);

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * redis 用不用都无所谓
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("token");
        String username = request.getHeader("username");
        boolean result = this.checkUserIsLogin(header,username);
        if(!result){
            response.sendRedirect("http://localhost:1001/spring-cloud-user/user/error");
        }
        return true;
    }

    /**
     * 验证用户登录信息
     * @param header
     * @return
     */
    private boolean checkUserIsLogin(String header,String username){

        if(StringUtils.isBlank(header) || StringUtils.isBlank(username)){
            LOGGER.error("请求头header is null");
            return false;
        }

        String token = null;
        try {
             token = redisTemplate.opsForValue().get(username).toString();
        } catch (Exception e) {
            return false;
        }

        if(!header.equals(token)){
            return false;
        }

        return true;
    }
}
