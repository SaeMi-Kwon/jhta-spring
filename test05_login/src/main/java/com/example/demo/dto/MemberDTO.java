package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTO {
    private String id;
    private String pwd;
    private String email;
    private int age;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regdate;
}
