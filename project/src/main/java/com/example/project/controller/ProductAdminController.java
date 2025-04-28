package com.example.project.controller;


import com.example.project.dto.ProductDto;
import com.example.project.dto.ProductRequestDto;
import com.example.project.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductAdminController {

    private final ProductService productService;

    @GetMapping("/insert")
    public void insertForm(){}

    @PostMapping("/insert")
    public String insert(ProductRequestDto productRequestDto) throws IOException {
        productService.insert(productRequestDto);
        return "redirect:/product/list";
    }

    @GetMapping("/list")
    public void list(Model model){
        List<ProductDto> list = productService.list();
        System.out.println("상품 목록:" + list);
        model.addAttribute("list",list);
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("pid")int pid){
        productService.delete(pid);
        return "redirect:/product/list";
    }

    @GetMapping("/update")
    public void updateForm(@RequestParam("pid")int pid,Model model){
        ProductDto dto = productService.select(pid);
        model.addAttribute("dto",dto);
    }

    @PostMapping("/update")
    public String update(ProductRequestDto productRequestDto) throws IOException{
        System.out.println(productRequestDto);
        productService.update(productRequestDto);
        return "redirect:/product/list";
    }

}
