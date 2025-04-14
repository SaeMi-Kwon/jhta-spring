package com.example.demo.config;

import com.example.demo.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean loginFilter(){
        FilterRegistrationBean<LoginFilter> bean=new FilterRegistrationBean<>();
        bean.setFilter(new LoginFilter());
        bean.setOrder(1);
        bean.addUrlPatterns("/*");
        return bean;
    }

}
