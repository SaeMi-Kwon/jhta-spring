package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;

@Controller
public class MemberInsertController {

    @Autowired
    private MemberService service;

    @GetMapping("/member/insert")
    public String insertForm(Model model){
        model.addAttribute("memberDTO", new MemberDTO());
        return "member/insertForm";
    }

    @PostMapping("/member/insert")
    public String insert(@Valid MemberDTO memberDTO, Errors errors){

        if(errors.hasErrors()){
           return "member/insertForm";
        }

        service.insert(memberDTO);
        return "home";
    }
}
