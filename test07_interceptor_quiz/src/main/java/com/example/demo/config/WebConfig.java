package com.example.demo.config;

import com.example.demo.interceptor.AdminCheckInterceptor;
import com.example.demo.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/member/**","/css/**"); //로그인 안해도 되는 경로

        registry.addInterceptor(new AdminCheckInterceptor())
                .order(2)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/","/member/**","/css/**","/item/**");  //관리자체크 안해도 되는 경로
    }

}
