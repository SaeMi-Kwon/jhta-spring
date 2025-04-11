package com.example.demo.controller;

import com.example.demo.dto.MembersDTO;
import com.example.demo.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberUpdateController {

    @Autowired
    private MembersService service;

    @GetMapping("/member/update")
    public String updateForm(@RequestParam("num")int num, Model model){
        MembersDTO dto=service.getinfo(num);
        model.addAttribute("dto",dto);
        return "member/update";
    }

    //방법1
//    @PostMapping("/member/update")
//    public String update(MembersDTO dto){
//        int n=service.update(dto);
//        System.out.println(n + "명의 회원수정");
//        return "redirect:/member/list";
//    }

    //방법2
    @PostMapping("/member/update")
    public String update(@ModelAttribute MembersDTO dto){
        int n=service.update(dto);
        System.out.println(n + "명의 회원수정");
        return "member/result";
    }
}
