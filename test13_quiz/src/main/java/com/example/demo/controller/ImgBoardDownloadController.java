package com.example.demo.controller;

import com.example.demo.dto.ImgBoardDto;
import com.example.demo.service.ImgBoardService;
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
public class ImgBoardDownloadController {

    private final ImgBoardService service;

    @Value("${file.path}")
    private String path;

    @GetMapping("/imgboard/download")
    public ResponseEntity<Resource> download(@RequestParam("fnum")long fnum) throws MalformedURLException {
        ImgBoardDto dto = service.select(fnum);

        String savefile = dto.getSavefilename();
        String orgfile = dto.getOrgfilename();

        String filename = UriUtils.encode(orgfile, StandardCharsets.UTF_8);
        UrlResource resource = new UrlResource("file:" + path + File.separator + savefile);
        String content_disposition = "attachment;filename=\"" + filename + "\"";

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,content_disposition)
                .body(resource);
    }
}

