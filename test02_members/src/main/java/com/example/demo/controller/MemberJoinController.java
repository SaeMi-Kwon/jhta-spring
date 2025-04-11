package com.example.demo.controller;

import com.example.demo.dto.MembersDTO;
import com.example.demo.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberJoinController {

    @Autowired
    private MembersService service;

    @GetMapping("/member/join")
    public String joinForm(){
        return "member/join";
    }

    @PostMapping("/member/join")
    public String join(MembersDTO dto){
        // db에 저장
        service.insert(dto);

        // 뷰페이지로 이동
        return "member/result";
    }
}
