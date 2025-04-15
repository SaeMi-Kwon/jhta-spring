package com.example.demo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    //로그인 했으면 요청페이지로 이동 / 로그인 안했으면 로그인페이지로 이동
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //세션에 아이디가 존재하는 검사
        HttpSession session=request.getSession();

        //로그인 안했으면
        if(session == null || session.getAttribute("id") == null){
            //로그인페이지 이동
            response.sendRedirect(request.getContextPath()+"/member/login");
            return false;
        }

        return true;
    }

}
