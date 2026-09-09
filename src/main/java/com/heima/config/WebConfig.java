package com.heima.config;


import com.heima.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * 拦截器配置类
 */
//@Configuration
public class WebConfig implements WebMvcConfigurer {
//    // 拦截器
////    @Autowired
//    private TokenInterceptor tokenInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**")
//                .excludePathPatterns("/login");// /** 表示所有请求
//    }
}
