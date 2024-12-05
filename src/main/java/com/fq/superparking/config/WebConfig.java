package com.fq.superparking.config;

import com.fq.superparking.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .excludePathPatterns("/user/login") // 不拦截那些路径
                .excludePathPatterns("/user/captcha")
                .excludePathPatterns("/doc.html")
                .excludePathPatterns("/**/*.html")
                .excludePathPatterns("/**/*.css")
                .excludePathPatterns("/**/*.js")
                .excludePathPatterns("/phone/phoneCode")
                .excludePathPatterns("/phone/login")
                .excludePathPatterns("/swagger-resources")
                .excludePathPatterns("/v3/**")
                .addPathPatterns("/**"); // 要拦截那些路径
    }
}
