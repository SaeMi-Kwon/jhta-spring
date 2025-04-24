package com.example.project.controller;

import com.example.project.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cartitem")
public class CartItemController {

    private final CartItemService cis;

    @GetMapping("/insert")
    public String insert(@RequestParam("pid")int pid){

        return "cartitem/insert";
    }
}
