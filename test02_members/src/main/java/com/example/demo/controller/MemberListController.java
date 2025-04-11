package com.example.demo.controller;

import com.example.demo.dto.MembersDTO;
import com.example.demo.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MemberListController {

    @Autowired
    private MembersService service;

    //방법1
//    @GetMapping("/member/list")
//    public String list(Model model){
//        //db데이터 얻어와 모델객체에 담기
//        List<MembersDTO>list = service.selectAll();
//        model.addAttribute("list",list);
//        //뷰페이지로 이동
//        return "member/list";
//    }

    //방법2(예전에 많이 사용함)
    @GetMapping("/member/list")
    public ModelAndView list1(){
        ModelAndView mv=new ModelAndView();
        mv.addObject("list",service.selectAll());
        mv.setViewName("member/list");
        //뷰페이지로 이동
        return mv;
    }

}
