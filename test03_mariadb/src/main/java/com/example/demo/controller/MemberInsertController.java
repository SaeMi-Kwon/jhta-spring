package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.mapper.MemberMapper;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberInsertController {

    @Autowired
    private MemberService service;

    @GetMapping("/member/insert")
    public String insertForm(){
        return "member/insertForm";
    }

    @PostMapping("/member/insert")
    public String insert(MemberDTO dto){
        service.insert(dto);
        return "home";
    }
}
