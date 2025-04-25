package com.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartProductDto {
    private int cmid;
    private int mid; //사용자 id
    private int pid;  //상품id
    private String name;  //상품명
    private String img;  //이미지
    private int price;   //상품가격
    private int quantity;   //담는거 수량
    private int totalprice;  //총 가격
    private Date added_at;


}
