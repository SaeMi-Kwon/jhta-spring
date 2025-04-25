package com.example.project.controller;

import com.example.project.dto.MemberDto;
import com.example.project.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public void joinForm(){}

    @PostMapping("/join")
    public String join(MemberDto dto, Model model){
        memberService.insert(dto);
        return "home";
    }

    @GetMapping("/login")
    public void login(@AuthenticationPrincipal UserDetails mem, Model model){
        model.addAttribute("mem",mem);
    }

}
