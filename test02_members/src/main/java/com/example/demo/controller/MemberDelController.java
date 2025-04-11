package com.example.demo.controller;

import com.example.demo.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberDelController {

    @Autowired
    private MembersService service;
    
    @GetMapping("/member/del")
    public String del(@RequestParam("num")int num){
        int n=service.delete(num);
        System.out.println(n + "명의 회원 삭제!");
        //return "member/result";

        //리다이렉트방식으로 컨트롤러 호출하기
        return "redirect:/member/list";
    }
}
