package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    //@AuthenticationPrincipal : 시큐리티가 사용하는 컨텍스트영역에서 UserDetails객체 얻어오기
    //로그인하지 않았다면 null, 로그인했다면 사용자 정보가 자동으로 주입
    public String home(@AuthenticationPrincipal UserDetails user, Model model){
        model.addAttribute("user",user);
        return "home";
    }

}
