package com.example.demo.config;

import com.example.demo.interceptor.LogInterceptor;
import com.example.demo.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //인터셉터 등록하기
        registry.addInterceptor(new LogInterceptor())
                .order(1)  //여러 인터셉터가 있을때 실행되는 순서
                .addPathPatterns("/**")  //하위 모든 경로를 요청시마다 인터셉터 수행
                .excludePathPatterns("/css/**","/*.ico");  //인터셉터가 동작되지 않을 경로 지정

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                //.addPathPatterns("/item/list1","/item/list2")
                .addPathPatterns("/**")
                .excludePathPatterns("/","/member/**","/css/**");
    }


}
