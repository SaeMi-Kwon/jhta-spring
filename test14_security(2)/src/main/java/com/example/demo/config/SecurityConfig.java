package com.example.demo.config;

import com.example.demo.security.CustomUserDetailsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    CustomUserDetailsService detailsService;

    //비밀번호를 암호화 하기 위한 Bean등록
    @Bean  
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SavedRequestAwareAuthenticationSuccessHandler();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrfConfigurer -> {
            csrfConfigurer.disable(); //csrf토큰값 사용안하기
        });
        http.authorizeHttpRequests(authz->{
           authz.requestMatchers("/member/**").authenticated() //member/** 요청경로는 인증된 사용자만 허용
                .requestMatchers("/admin/**").hasAuthority("ADMIN")// admin/**요청경로는 ADMIN 권한이있는 사용자만 허용
                .anyRequest().permitAll(); // 나머지 요청경로는모든 권한 허용
        });

        http.formLogin(httpSecurityFormLoginConfigurer ->{
            httpSecurityFormLoginConfigurer
                   // .usernameParameter("id") username파라미터 이름이 id인 경우
                   // .passwordParameter("pwd") password파라미터 이름이 pwd인 경우
                    .loginPage("/login") //로그인페이지 경로 지정
                    .loginProcessingUrl("/login") //로그인 처리 경로 지정(이 경로로 요청이 들어오면 스프링시큐리티가 인증처리)
                    .successHandler((request, response, authentication) -> { //로그인이 성공적으로 수행했을때 처리할 핸들러
                        System.out.println(request.getParameter("username"));
                        //세션에 username담기
                        request.getSession().setAttribute("username",request.getParameter("username"));
                        //홈으로 이동하기
                        response.sendRedirect(request.getContextPath()+"/");
                    })
                 //   .defaultSuccessUrl("/",true) //로그인이 성공했을때 이동할 페이지
                    .permitAll();
        });

        http.logout(httpSecurityLogoutConfigurer -> {
            httpSecurityLogoutConfigurer
                  //  .logoutUrl("/logout");
                    //.logoutSuccessUrl("/")
                    // 로그아웃 핸들러 추가 (세션 무효화 처리)
                    .addLogoutHandler((request, response, authentication) -> {
                        HttpSession session = request.getSession();
                        session.invalidate();
                    })
                    // 로그아웃 성공 핸들러 추가 (리다이렉션 처리)
                    .logoutSuccessHandler((request, response, authentication) ->
                            response.sendRedirect("/"))
                    .deleteCookies("JSESSIONID");//JSESSIONID삭제하기


        });

        http.userDetailsService(detailsService);

        return http.build();
    }
}










