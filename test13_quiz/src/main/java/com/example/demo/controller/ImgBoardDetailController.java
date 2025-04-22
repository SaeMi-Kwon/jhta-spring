package com.example.demo.controller;

import com.example.demo.service.ImgBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ImgBoardDetailController {

    private final ImgBoardService service;

    @GetMapping("/imgboard/detail")
    public void detail(@RequestParam("fnum")long fnum, Model model){
        model.addAttribute("dto",service.select(fnum));
    }
}
