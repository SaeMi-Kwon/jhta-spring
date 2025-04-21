package com.example.demo.controller;

import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MovieService service;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("mlist",service.MovieList());
        return "home";
    }
}
