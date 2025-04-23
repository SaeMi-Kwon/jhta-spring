package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardRequestDto {
    private long fnum;
    private String id;
    private String title;
    private String content;
    private MultipartFile file1;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
