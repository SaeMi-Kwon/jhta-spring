package com.example.demo.controller;

import com.example.demo.dto.BoardDto;
import com.example.demo.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardInsertController {

    private final BoardService service;

    @GetMapping("/insert")
    public String insertForm(){
        return "board/insert";
    }

    @PostMapping("/insert")
    public String insert(BoardDto boardDto, Model model){
        BoardDto dto = service.insert(boardDto);
        if(dto != null){
            model.addAttribute("result","success");
        }else {
            model.addAttribute("result","fail");
        }
        return "board/result";
    }
}
