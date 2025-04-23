package com.example.demo.config;

import com.example.demo.security.CustomOAth2UserDetailsService;
import com.example.demo.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  //Spring Security의 웹 보안 기능을 활성화
public class SecurityConfig { //Spring Security 5.7 이상에서 권장하는 방식

    @Autowired
    CustomUserDetailsService detailsService;
    @Autowired
    CustomOAth2UserDetailsService customOAth2UserDetailsService;

    //비밀번호를 암호화 하기 위한 Bean등록
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //security설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrfConfigurer -> {
           csrfConfigurer.disable();   //csrf토큰값 사용 안하기
            // 타임리프방식 th:action,GET방식말고는 ajax(POST,PUT,DELETE)들에게는 항상 직접 설정해서 보내줘야하기때문에 번거로워서 일단 해제
        });

        http.authorizeHttpRequests(authz -> {
            // member/** 요청경로는 인증(로그인)된 사용자만 허용
            authz.requestMatchers("/member/**").authenticated()
                   // admin/** 요청경로는 ADMIN 권한이 있는 사용자만 허용
                   .requestMatchers("/admin/**").hasAuthority("ADMIN") //user.getRole()에서 반환한 값과 hasAuthority()가 매칭
                   .anyRequest().permitAll();  //나머지 요청경로는 모든 권한 허용(비로그인도 가능)
        });

        http.formLogin(formLoginConfigurer -> {
            formLoginConfigurer
                    .loginPage("/login") //로그인페이지 경로 지정
                    //실제 로그인 처리를 수행하는 th:action(이 경로는 스프링 시큐리티가 가로채서 내부적으로 인증 처리)
                    .loginProcessingUrl("/loginOk")  //로그인 처리 경로 지정(이 경로로 요청이 들어오면 스프링시큐리티가 인증처리)
                    .defaultSuccessUrl("/",true)  //로그인에 성공했을때 이동할 페이지(true는 무조건 여기로 이동하게 만듦)
                    .permitAll();  //로그인 페이지, 로그인 처리 경로 모두 접근 허용(비로그인 상태에서도 가능)
        });

        http.logout(logoutConfigurer -> {
            logoutConfigurer
                    //.logoutUrl("/logout");  //디폴트가 POST만 허용
                    .logoutSuccessUrl("/");  //로그아웃 후에 이동할 경로
        });

        //로그인 시 CustomUserDetailsService.loadUserByUsername() 메서드를 사용해서 유저 정보를 로딩하게 만듭니다.
        http.userDetailsService(detailsService);


        //소셜로그인 설정
        http.oauth2Login(OAuth2LoginConfigurer -> {
            OAuth2LoginConfigurer.loginPage("/login");
            OAuth2LoginConfigurer.loginProcessingUrl("/login/oauth2/code/naver"); //redirect_uri 설정한 경로
            OAuth2LoginConfigurer.userInfoEndpoint(userInfoEndpointConfig -> {
                //OAuth2 로그인 성공후 사용자 정보를 가져와서 해야 할 일 작성
                userInfoEndpointConfig.userService(customOAth2UserDetailsService);
            });

            OAuth2LoginConfigurer.defaultSuccessUrl("/").permitAll();
        });

        return http.build();
    }



}
