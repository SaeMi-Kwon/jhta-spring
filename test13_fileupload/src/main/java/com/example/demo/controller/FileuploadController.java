package com.example.demo.controller;

import com.example.demo.dto.FileRequestDto;
import com.example.demo.dto.FileinfoDto;
import com.example.demo.service.FileinfoService;
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
@RequestMapping("/file")
public class FileuploadController {

    private final FileinfoService fileinfoService;

    //application.properties 파일에 설정된 값 읽어오기
    @Value("${file.path}")   //import spring
    private String filepath;   //c:\\upload

    @GetMapping("/upload")
    public String uploadForm(){
        return "file/upload";
    }

    @PostMapping("/upload")
    public String uploadOk(FileRequestDto requestDto){
        MultipartFile file1 = requestDto.getFile1();
        File f = new File(filepath);
        if(!f.exists()) f.mkdir();   //c:\\upload폴더가 없으면 폴더 생성하기

        //전송된 파일명
        String orgFileName=file1.getOriginalFilename();
        //저장할 파일명(중복되지 않는 파일명 만들기)
        String saveFileName = UUID.randomUUID() + "_" + orgFileName;
        long fileSize=file1.getSize();  //파일크기
        File ff = new File(filepath + File.separator + saveFileName);

        try{
            file1.transferTo(ff);  //파일 복사하기(file1 읽어서 ff에 저장한다)
            System.out.println("파일업로드 완료!");

            //DB에 파일정보 저장
            FileinfoDto dto = FileinfoDto.builder()
                    .writer(requestDto.getWriter())
                    .title(requestDto.getTitle())
                    .content(requestDto.getContent())
                    .orgfilename(orgFileName)
                    .savefilename(saveFileName)
                    .filesize(fileSize)
                    .build();
            fileinfoService.insert(dto);

        }catch (IOException ie){
            System.out.println(ie.getMessage());
        }

        return "result";
    }

}
