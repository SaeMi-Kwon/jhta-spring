package com.example.demo.controller;

import com.example.demo.dto.MemberDto;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member")
public class UpdateController {

    @Autowired
    private MemberService service;

    @GetMapping("/update")
    public String updateForm(@RequestParam("id")String id, Model model){
        MemberDto dto = service.select(id);
        model.addAttribute("dto",dto);
        return "member/update";
    }

    @PostMapping("/update")
    public String update(MemberDto memberDto){
        MemberDto dto=service.update(memberDto);
        System.out.println(dto);
        return "redirect:/member/list";
    }
}
