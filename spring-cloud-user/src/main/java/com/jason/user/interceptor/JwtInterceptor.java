package com.jason.user.interceptor;

import com.jason.consts.RedisConstant;
import com.jason.utils.CurremtLimitingUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

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
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 做一些限流的操作
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = CurremtLimitingUtils.getIPAddress(request);
        String flag = this.rateOfFlow(ipAddress);
        if("TIME_OUT".equals(flag)){
            response.sendRedirect("http://localhost:1001/spring-cloud-user/test/error?message="+"TIME_OUT");
        }
        return true;
    }

    /**
     * 限流
     * @param ipAddress
     * @return
     */
    public String  rateOfFlow(String ipAddress){
        /**
         * 验证黑名单
         */
        if(stringRedisTemplate.hasKey(RedisConstant.BLACK_LIST +ipAddress)){
            String message = "ip:" + ipAddress + "操作太快了，请一分钟以后进行尝试";
            LOGGER.info(message);
            return "TIME_OUT";
        }
        /**
         * 验证ip是否存在 然后计数
         */
        if(stringRedisTemplate.hasKey(ipAddress)){
            if( stringRedisTemplate.getExpire(ipAddress)>-1){
                Long increment = stringRedisTemplate.opsForValue().increment(ipAddress, 1L);
                if(increment >= 10){
                    /**
                     * 访问受限 加入黑名单...
                     */
                    stringRedisTemplate.opsForValue().set(RedisConstant.BLACK_LIST+ipAddress,ipAddress, 1L,TimeUnit.MINUTES);
                }
            }else{
                stringRedisTemplate.delete(ipAddress);
            }
        }else{
            //设置过期时间
            stringRedisTemplate.opsForValue().set(ipAddress,"1",10L,TimeUnit.SECONDS);
        }
        return "SUCCESS";
    }
}
