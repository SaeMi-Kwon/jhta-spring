package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SumController {

    @GetMapping("/sum")
    public String sumForm(){
        return "sum";
    }

    @PostMapping("/sum")
    public String sum(@RequestParam("num1")int num1, @RequestParam("num2")int num2, Model model){
        //int num3 = num1 + num2;
        model.addAttribute("num3",num1 + num2);
        return "showSum";

    }
}

