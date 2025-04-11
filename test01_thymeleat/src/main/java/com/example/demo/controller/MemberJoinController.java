package com.example.demo.controller;

import com.example.demo.dto.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberJoinController {

    @GetMapping("/member/join")
    public String joinForm(){
        return "member/join";
    }

//    @PostMapping("/member/join")
//    public String join(Member mem, Model model){
//        System.out.println(member);
//        System.out.println("db저장.....!");
//        model.addAttribute("member",mem);
//        return "member/result";
//    }

    @PostMapping("/member/join")
    public String join(@ModelAttribute Member mem){
        System.out.println("db저장.....!");
        return "member/result";
    }

}
