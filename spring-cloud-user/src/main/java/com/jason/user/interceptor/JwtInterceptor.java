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
        String s = JwtUtil.checkToken(header);
        if(StringUtils.isBlank(s)){
           /* String[] split = s.split(",");
            String token = null;
            try {
                token = redisTemplate.opsForHash().get("token", split[0]).toString();
            } catch (Exception e) {
                e.printStackTrace();

            }
            if(header.equals(token)){
                LOGGER.info("true");
            }*/
            response.sendRedirect("http://localhost:1001/spring-cloud-user/user/error");
        }
        return true;
    }
}
