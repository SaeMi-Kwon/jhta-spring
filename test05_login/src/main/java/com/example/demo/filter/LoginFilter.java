package com.example.demo.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginFilter implements Filter {

    //filter가 동작되지 않을 uri(경로) 설정
    String[] whiteList={"/","/member/login","/member/join","/member/logout","/css"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //로그인체크
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpSession session=request.getSession();

        HttpServletResponse response=(HttpServletResponse) servletResponse;

        String uri=request.getRequestURI();  //요청 url 받아오기
        System.out.println("요청 uri ==>" + uri);
        if(isLoginCheck(uri)){  //로그인한 사용자만 볼 수 있는 페이지 -> 로그인했는지 검사함
            if(session==null || session.getAttribute("dto")==null){
                //로그인페이지로 이동
                response.sendRedirect(request.getContextPath()+"/member/login");
                return;
            }

        }

        //요청페이지로 이동
        filterChain.doFilter(servletRequest,servletResponse);
    }

    private boolean isLoginCheck(String requestURI){  //member/login
        return !PatternMatchUtils.simpleMatch(whiteList,requestURI);
    }
}
