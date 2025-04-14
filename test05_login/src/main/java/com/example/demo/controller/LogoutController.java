package com.example.demo.controller;

import com.example.demo.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @Autowired
    private MemberService service;

    @GetMapping("/member/logout")
    public String logout(HttpServletRequest request
                        //, HttpSession session
                        ){

        //request.getSession().invalidate();
        request.getSession().removeAttribute("dto");


        //session.invalidate();
        return "redirect:/";
    }
}
