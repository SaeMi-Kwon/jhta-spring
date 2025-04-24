package com.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequestDto {
    private int pid;
    private String name;
    private int price;
    private int amount;  //재고수량
    private MultipartFile img;  //이미지
    private Date regdate;
}
