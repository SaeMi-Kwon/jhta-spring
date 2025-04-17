package com.example.demo.controller;

import com.example.demo.dto.MemberDto;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class InsertController {

    @Autowired
    private MemberService service;

    @GetMapping("/join")
    public String insertForm(){
        return "member/join";
    }

    @PostMapping("/join")
    public String insert(MemberDto dto, Model model){
        try{
            service.insert(dto);
            model.addAttribute("result","success");
        }catch (Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("result","fail");
        }
        return "member/result";
    }
}
