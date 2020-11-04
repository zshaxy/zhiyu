package com.axy.zhiyu.interceptor;

import com.axy.zhiyu.commonutils.CookieUtil;
import com.axy.zhiyu.commonutils.JwtUtil;
import com.axy.zhiyu.commonutils.WebConst;
import com.axy.zhiyu.config.tokenKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
@EnableConfigurationProperties(tokenKey.class)
public class AuthInterceptor  extends HandlerInterceptorAdapter {


    @Autowired
    private tokenKey tokenKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtil.getCookieValue(request, "token", false);
        if (token != null && token.length() > 0) {
            Map<String, Object> param = JwtUtil.decode(token, tokenKey.getTokenKey(), tokenKey.getSalt());
        }

//        return super.preHandle(request, response, handler);\
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
