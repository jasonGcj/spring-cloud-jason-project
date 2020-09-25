package com.jason.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.jason.consts.LoginStateConstant;
import com.jason.consts.RedisConstant;
import com.jason.domain.UserEntity;
import com.jason.user.IUser;
import com.jason.user.UserContext;
import com.jason.utils.CurremtLimitingUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 登录过滤器
 * @ClassName LoginFilter
 * @Description TODO
 * @Author GCJ
 * @Date 2019/11/11 16:44
 */
@Component
public class LoginFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);

    public static final String USER_URL ="/spring-cloud-user/user";

    public static final String ARTICLE_URL ="/spring-cloud-article/image";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 过滤器类型，前置过滤器
     * @return
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 过滤器顺序，越小越先执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 过滤器是否生效
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        /**
         * 这些url不会验证登录规则
         */
        if(request.getRequestURI().contains(USER_URL) || request.getRequestURI().contains(ARTICLE_URL)
        ){
            return false;
        }
        return true;
    }

    /**
     * 做一些网关上的效验
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //JWT
        RequestContext requestContext =  RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String header = request.getHeader("token");
        String username = request.getHeader("username");
        boolean result = this.checkUserIsLogin(header,username);
        String ipAddress = CurremtLimitingUtils.getIPAddress(request);
        if(!result){
            LOGGER.info(ipAddress+":用户未登录");
            //设置为false则不往下走(不调用api接口)
            requestContext.setSendZuulResponse(false);
            //响应一个状态码：401
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            requestContext.setResponseBody(LoginStateConstant.NO_LOGIN);
        }
        LOGGER.info(ipAddress+":效验通过开始访问......."+":"+request.getServerName());
        return true;
    }

    /**
     * 验证用户登录信息
     * @param header
     * @return
     */
    private boolean checkUserIsLogin(String header,String username){

        if(StringUtils.isBlank(header) || StringUtils.isBlank(username)){
            return false;
        }

        String token = null;
        try {
            token = stringRedisTemplate.opsForValue().get(RedisConstant.LOGIN+username).toString();
        } catch (Exception e) {
            return false;
        }

        if(!header.equals(token)){
            return false;
        }
        return true;
    }
}
