package com.example.project.config;

import com.example.project.security.CustomMemberDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomMemberDetailsService detailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrfConfigurer -> {
            csrfConfigurer.disable(); //csrf토큰값 사용 안하기
        });

        http.authorizeHttpRequests(authmr -> {
            authmr.requestMatchers("/member/list").authenticated()
                    .requestMatchers("/member/update").authenticated()
                    .requestMatchers("/member/delete").authenticated()
                    .requestMatchers("/cartitem/**").authenticated()
                    .requestMatchers("/product/insert").hasAuthority("ADMIN")
                    .requestMatchers("/product/update").hasAuthority("ADMIN")
                    .requestMatchers("/product/delete").hasAuthority("ADMIN")
                    .anyRequest().permitAll();
        });

        http.formLogin(formLogin -> {
           formLogin.loginPage("/member/login")
                   .loginProcessingUrl("/loginOk")
                   .defaultSuccessUrl("/product/list")
                   .permitAll();
        });

        http.logout(logoutConfig -> {
            logoutConfig.logoutUrl("/logout")
                    .logoutSuccessUrl("/");
        });

        http.userDetailsService(detailsService);
        return http.build();
    }

}
