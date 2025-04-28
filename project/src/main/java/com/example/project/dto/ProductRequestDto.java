package com.example.project.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductRequestDto {
    private int pid;
    private String name;
    private int price;
    private int amount;  //재고수량
    private MultipartFile img;  //이미지
    private Date regdate;
}
