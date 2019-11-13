package com.jason.user.interceptor;

import com.jason.utils.JwtUtil;
import org.apache.commons.lang.StringUtils;
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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器");
        String header = request.getHeader("token");
        String s = JwtUtil.checkToken(header);
        if(StringUtils.isBlank(s)){
            return false;
        }
        System.out.println("放行");
        return true;
    }
}
