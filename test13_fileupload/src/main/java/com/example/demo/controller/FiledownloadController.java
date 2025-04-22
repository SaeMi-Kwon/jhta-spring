package com.example.demo.controller;

import com.example.demo.dto.FileinfoDto;
import com.example.demo.service.FileinfoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
public class FiledownloadController {

    private final FileinfoService fileinfoService;

    @Value("${file.path}")
    private String filepath;

    @GetMapping("/file/download")  //Resource -> import spring.core.io
    public ResponseEntity<Resource> download(@RequestParam("filenum")long filenum) throws MalformedURLException {
        FileinfoDto dto =fileinfoService.select(filenum);
        String savefilename= dto.getSavefilename();
        String orgfilename=dto.getOrgfilename();

        //파일명이 한글이 깨지지 않도록 인코딩
        String filename = UriUtils.encode(orgfilename, StandardCharsets.UTF_8);
        //파일을 객체로 불러오기
        UrlResource resource = new UrlResource("file:"+ filepath +File.separator +savefilename);
        //attachment : 강제로 다운로드("직접 열지 말고, 다운로드해야 하는 파일로 인식)
        String contentDisposition="attachment;filename=\"" + filename + "\"";

        return ResponseEntity
                .ok()  //200 OK
                .header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition)  //브라우저가 파일을 다운로드하도록 유도
                .body(resource);   //실제로 읽어올 객체 응답으로 보내기(Resource객체)
    }
}
