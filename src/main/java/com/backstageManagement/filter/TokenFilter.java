package com.backstageManagement.filter;

import com.backstageManagement.utils.CurrentHolder;
import com.backstageManagement.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
@Slf4j
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1.获取请求路径
        String requestURI = request.getRequestURI();

        //2.是否是登陆请求 请求头有/login，说明是登陆路径
        if (requestURI.contains("/login")) {
            log.info("登陆请求：{}", requestURI);
            filterChain.doFilter(request, response);
            return;
        }
        //3.获取请求头的token
        String token = request.getHeader("token");
        //4.判断token是否存在 不存在返回401状态码
        if (token == null || token.isEmpty()) {
            log.info("请求头token不存在");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //5.如果token存在，校验令牌 失败返回401状态码
        try {
            Claims claims = JwtUtils.parseJWT(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("当前用户id为：{},将其存入ThreadLocal", empId);

        } catch (Exception e) {
            log.info("令牌非法");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //6.校验通过则放行
        log.info("令牌校验通过");
        filterChain.doFilter(request, response);

        //7.删除ThreadLocal中的数据
        CurrentHolder.remove();
    }
}
