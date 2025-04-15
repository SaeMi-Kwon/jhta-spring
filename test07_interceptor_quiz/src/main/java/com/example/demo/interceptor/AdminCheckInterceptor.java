package com.example.demo.interceptor;

import com.example.demo.dto.MembersDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class AdminCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session= request.getSession();

//        String role = (String) session.getAttribute("role");
//        if(session != null && session.getAttribute("id") !=null && role.equals("MEMBER")){
//            response.sendRedirect(request.getContextPath()+"/");
//        }
//
//        return true;

        if(session != null && session.getAttribute("id") != null){
            String role = (String) session.getAttribute("role");

            if(!role.equals("ADMIN")){
                response.sendRedirect(request.getContextPath()+"/forbidden");
                return false;
            }else {
                return true;
            }
        }else {
            response.sendRedirect(request.getContextPath()+ "/member/login");
            return false;
        }


    }
}
