package com.example.project.config;

import com.example.project.repository.CartRepository;
import com.example.project.security.CustomMemberDetailsService;
import jakarta.servlet.http.HttpServletResponse;
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
    @Autowired
    private CartRepository cartRepository;

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
                    .requestMatchers("/cart/**").authenticated()
                    .requestMatchers("/product/insert").hasAuthority("ADMIN")
                    .requestMatchers("/product/update").hasAuthority("ADMIN")
                    .requestMatchers("/product/delete").hasAuthority("ADMIN")
                    .requestMatchers("/product/list").hasAuthority("ADMIN")
                    .anyRequest().permitAll();
        });

        http.exceptionHandling(ex -> ex
                .authenticationEntryPoint((request, response, authException) -> {
                    // AJAX 요청이라면 401 응답
                    String ajaxHeader = request.getHeader("X-Requested-With");
                    if ("XMLHttpRequest".equals(ajaxHeader)) {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다.");
                    } else {
                        response.sendRedirect("/member/login");
                    }
                })
        );

        http.formLogin(formLogin -> {
           formLogin.loginPage("/member/login")
                   .loginProcessingUrl("/loginOk")
                   .defaultSuccessUrl("/productsmove")
                   .permitAll();
        });

        http.logout(logoutConfig -> {
            logoutConfig.logoutUrl("/logout")
                    .logoutSuccessUrl("/");
        });

//        http.logout(logout -> logout
//                .logoutUrl("/logout")
//                .addLogoutHandler((request, response, authentication) -> {
//                    if (authentication != null && authentication.getPrincipal() instanceof CustomMemberDetails user) {
//                        int mid = user.getMid();
//                        cartRepository.deleteByMemberId(mid); // 장바구니 비우기
//                    }
//                })
//                .logoutSuccessUrl("/")
//        );

        http.userDetailsService(detailsService);
        return http.build();
    }

}
