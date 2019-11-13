package com.jason.zuul.filter;

import com.jason.utils.JwtUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    RedisTemplate redisTemplate;

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
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //JWT
        RequestContext requestContext =  RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        //token对象
        String token = request.getHeader("token");

        //如果token字符串为空
        if(StringUtils.isBlank((token))){
            token  = request.getParameter("token");
        }

        String User = JwtUtil.checkToken(token);

        //登录校验逻辑  根据公司情况自定义 JWT
        if (StringUtils.isBlank(User)) {
            //设置为false则不往下走(不调用api接口)
            requestContext.setSendZuulResponse(false);
            //响应一个状态码：401
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            requestContext.setResponseBody("Token is fail");
        }
        return null;
    }
}
