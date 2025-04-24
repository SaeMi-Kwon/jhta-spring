package com.example.project.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.MalformedURLException;

@RestController
public class DataURLController {
    @Value("${file.path}")
    public String path;

    @GetMapping("/images/{filename}")
    public UrlResource showImg(@PathVariable("filename") String filename) throws MalformedURLException {
        return new UrlResource("file:" + path + File.separator + filename);
    }

}
