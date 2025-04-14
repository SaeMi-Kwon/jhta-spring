package com.example.demo.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie=new Cookie("id","");
        cookie.setPath("/");
        cookie.setMaxAge(0);  //쿠키유지시간 0으로 설정 - 쿠키삭제
        response.addCookie(cookie);

        request.getSession().removeAttribute("id");
        return "redirect:/";
    }
}
