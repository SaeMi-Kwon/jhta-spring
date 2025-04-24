package com.example.project.controller;


import com.example.project.dto.ProductDto;
import com.example.project.dto.ProductRequestDto;
import com.example.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService ps;

    @Value("${file.path}")
    private String path;

    @GetMapping("/insert")
    public void insertForm(){}

    @PostMapping("/insert")
    public String insert(ProductRequestDto productRequestDto) throws IOException {
        MultipartFile file = productRequestDto.getImg();
        File f = new File(path);
        if(!f.exists()) f.mkdir();

        String imgName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File fileSave = new File(path + File.separator + imgName);
        file.transferTo(fileSave);

        ProductDto dto = ProductDto.builder()
                .name(productRequestDto.getName())
                .amount(productRequestDto.getAmount())
                .price(productRequestDto.getPrice())
                .img(imgName)
                .build();

        ps.insert(dto);

        return "redirect:/";
    }

    @GetMapping("/list")
    public void list(Model model){
        List<ProductDto> list = ps.list();
        System.out.println("상품 목록:" + list);
        model.addAttribute("list",list);
    }
}
