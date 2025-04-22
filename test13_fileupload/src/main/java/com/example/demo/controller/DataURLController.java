package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.MalformedURLException;

/*
    Resource - Resource(외부 리소스 - 파일)에 대한 접근을 추상화한 인터페이스
    UrlResource - URL방식을 통해서 리소스의 위치(외부 파일)를 알려주는 Resource객체
 */
@RestController
public class DataURLController {

    @Value("${file.path}")
    private String filepath;

    @GetMapping("/images/{filename}")
    public UrlResource showImage(@PathVariable("filename")String filename) throws MalformedURLException{
        return new UrlResource("file:" + filepath + File.separator + filename);
    }
}
