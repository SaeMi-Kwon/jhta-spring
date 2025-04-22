package com.example.demo.controller;

import com.example.demo.dto.MultiFileRequestDto;
import com.example.demo.service.FileinfoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MultiFileuploadController {

    //private final FileinfoService fileinfoService;

    @Value("${file.path}")
    private String filepath;

    @GetMapping("/file/uploadFiles")
    public void uploadForm(){}

    @PostMapping("/file/uploadFiles")
    public String upload(MultiFileRequestDto dto) throws IOException {
        //다중파일 업로드 코드 완성해보세요
        List<MultipartFile> list = dto.getFile2();
        for(MultipartFile file :list){
            String orgfile = file.getOriginalFilename();
            String savefile = UUID.randomUUID() + "_" + orgfile;
            file.transferTo(new File(filepath + File.separator + savefile));
        }

        return "result";
    }
}
