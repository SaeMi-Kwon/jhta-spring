package com.example.demo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginFilter implements Filter {

    String[] whiteList={"/", "/member/login", "/member/logout", "/member/insert", "/css"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //아이디 체크
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        HttpSession session=request.getSession();

        String uri=request.getRequestURI();
        if(isLoginCheck(uri)){  //화이트리스트에 없으면 로그인화면으로 이동
            if(session == null || session.getAttribute("id") == null ){
                response.sendRedirect(request.getContextPath()+"/member/login");
                return;
            }
        }

        //요청페이지
        filterChain.doFilter(servletRequest,servletResponse);

    }

    private boolean isLoginCheck(String uri){
        return !PatternMatchUtils.simpleMatch(whiteList,uri);  //!(true) ==> false
    }
}
