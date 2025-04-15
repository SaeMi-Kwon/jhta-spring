package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/item")
public class ItemController {

    @GetMapping("/list1")
    public ModelAndView itemList1(){
        ArrayList<String> list=new ArrayList<>();
        list.add("상품1");
        list.add("상품2");
        list.add("상품3");

        ModelAndView mv=new ModelAndView();
        //뷰이름 지정
        mv.setViewName("item/list1");

        //뷰페이지로 이동할때 담아갈 모델객체 지정
        mv.addObject("list",list);
        return mv;

    }

    @GetMapping("/list2")
    public String itemList2(Model model){
        ArrayList<String> list=new ArrayList<>();
        list.add("사과");
        list.add("땅콩");
        list.add("수박");
        model.addAttribute("list",list);
        return "item/list2";
    }

}


