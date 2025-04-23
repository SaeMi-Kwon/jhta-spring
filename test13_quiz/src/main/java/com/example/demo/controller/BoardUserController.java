package com.example.demo.controller;

import com.example.demo.entity.BoardUser;
import com.example.demo.service.BoardUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BoardUserController {

    private final BoardUserService boardUserService;

    @GetMapping("/user/join")
    public void joinForm(){}

    @PostMapping("/user/join")
    public String join(BoardUser boardUser){
        boardUserService.save(boardUser);
        return "user/joinOk";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }


}
