package com.example.demo.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm(){
        return "member/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("id")String id,
                        @RequestParam("pwd")String pwd,
                        HttpServletResponse response,
                        HttpSession session){

        //아이디와 비밀번호가 db정보와 일치하는지 검사
        System.out.println("id==> " + id + ", pwd==> " + pwd);

        //로그인 - 아이디를 쿠키에 담기
        if(id.equals("hello") && pwd.equals("1234")){
            Cookie cookie=new Cookie("id",id);
            cookie.setMaxAge(60*3);  //쿠키유지시간(초단위 - 3분설정)
            cookie.setPath("/");  //쿠키가 전달되는 경로지정
            response.addCookie(cookie);  //응답객체에 쿠키 담기
            session.setAttribute("id",id);  //세션에 담기
            return "redirect:/";

        }
        return "member/login";
    }
}
