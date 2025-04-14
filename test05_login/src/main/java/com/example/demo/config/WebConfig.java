package com.example.demo.config;

import com.example.demo.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilter(){
        FilterRegistrationBean<LoginFilter> bean=new FilterRegistrationBean<>();
        bean.setFilter(new LoginFilter());
        bean.setOrder(1);   //필터의 실행 순서(우선순위)
        bean.addUrlPatterns("/*");   //모든 URL 요청에 대해 필터(LoginFilter)가 동작
        return bean;
    }
}
