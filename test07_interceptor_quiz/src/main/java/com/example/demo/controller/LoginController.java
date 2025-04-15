package com.example.demo.controller;

import com.example.demo.dto.MembersDTO;
import com.example.demo.service.MembersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping("/member")
public class LoginController {

    @Autowired
    private MembersService service;

    @GetMapping("/login")
    public String loginForm(){
        return "member/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("id")String id,
                        @RequestParam("pwd")String pwd,
                        HttpSession session){

        HashMap<String,String> map = new HashMap<>();
        map.put("id",id);
        map.put("pwd",pwd);
        MembersDTO dto = service.isMemberRole(map);

        if(dto!=null){
            session.setAttribute("id",dto.getId());
            session.setAttribute("role",dto.getRole());
        }else {
            return "member/login";
        }

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}
