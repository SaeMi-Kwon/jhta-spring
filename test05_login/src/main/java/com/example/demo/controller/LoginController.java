package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
public class LoginController {

    @Autowired
    private MemberService service;

    @GetMapping("/member/login")
    public String loginForm(){
        return "member/login";
    }

    @PostMapping("/member/login")
    public String login(@RequestParam("id")String id,
                        @RequestParam("pwd")String pwd,
                        HttpSession session
                        //, HttpServletRequest request
                        ){

        HashMap<String,Object> map=new HashMap<>();
        map.put("id",id);
        map.put("pwd",pwd);
        MemberDTO dto = service.isMember(map);
        if(dto==null){
            return "member/login";
        }
        //세션은 메모리 차지가 심하기떄문에 필요한 정보만 담아주는것이 좋다.
        session.setAttribute("dto",dto);
        //request.getSession().setAttribute("id",id);
        return "redirect:/";
    }

}
