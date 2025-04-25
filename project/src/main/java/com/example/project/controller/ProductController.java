package com.example.project.controller;


import com.example.project.dto.ProductDto;
import com.example.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Value("${file.path}")
    private String path;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> list(){
        List<ProductDto> list = productService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }



}
