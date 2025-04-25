package com.example.project.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class DataURLController {
    @Value("${file.path}")
    public String path;

    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> showImg(@PathVariable("filename") String filename) throws IOException {
        // 이미지 파일의 경로를 생성
        Path filePath = Paths.get(path).resolve(filename).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        // 파일이 존재하지 않으면 404 반환
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        // MIME 타입 추론
        String contentType = Files.probeContentType(filePath);
        if (contentType == null) {
            contentType = "application/octet-stream"; // 알 수 없을 때 기본값
        }

        // ResponseEntity로 이미지 파일과 MIME 타입 반환
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))  // 추론된 MIME 타입을 사용
                .body(resource);
    }

}
