package com.example.demo.controller;

import com.example.demo.service.ImgBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ImgBoardListController {

    private final ImgBoardService service;

    @GetMapping("/imgboard/list")
    public void list(Model model){
        model.addAttribute("list",service.list());
    }

}
