package com.heima.interceptor;

import com.heima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/*
 * 拦截器
 */
@Slf4j
//@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //1.获取请求路径
//        String requestURI = request.getRequestURI();
//
//        //2.是否是登陆请求 请求头有/login，说明是登陆路径
//        if (requestURI.contains("/login")) {
//            log.info("登陆请求：{}", requestURI);
//            return true;
//        }
        //3.获取请求头的token
        String token = request.getHeader("token");
        //4.判断token是否存在 不存在返回401状态码
        if (token == null || token.isEmpty()) {
            log.info("请求头token不存在");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //5.如果token存在，校验令牌 失败返回401状态码
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("令牌非法");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //6.校验通过则放行
        log.info("令牌校验通过");
        return true;
    }
}

