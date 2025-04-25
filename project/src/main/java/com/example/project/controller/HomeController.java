package com.example.project.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails mem, Model model){
        model.addAttribute("mem",mem);
        return "home";
    }

    @GetMapping("/productsmove")
    public String productPage(){
        return "products";
    }

    @GetMapping("/cart/view")
    public String cartList(){
        return "cart";
    }
}
