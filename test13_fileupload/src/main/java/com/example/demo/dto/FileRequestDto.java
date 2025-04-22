package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

//전송된 파일정보를 담기위한 클래스
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileRequestDto {
    private Long filenum;
    private String writer;
    private String title;
    private String content;
    //전송된 파일에 대한 정보를 갖는 객체
    private MultipartFile file1;  //form에 전송된 name값과 일치시켜야됨
}
