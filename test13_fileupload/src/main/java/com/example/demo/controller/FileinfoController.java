package com.example.demo.controller;

import com.example.demo.service.FileinfoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class FileinfoController {
    private final FileinfoService fileinfoService;

    @GetMapping("/file/info")
    public void fileinfo(@RequestParam("filenum")long filenum, Model model){
        model.addAttribute("dto",fileinfoService.select(filenum));
    }
}
