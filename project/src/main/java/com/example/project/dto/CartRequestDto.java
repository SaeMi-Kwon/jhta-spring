package com.example.project.dto;


import com.example.project.entity.Cart;
import com.example.project.entity.Member;
import com.example.project.entity.Product;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CartRequestDto {
    private int pid;  //상품id
    private int price;
    private int quantity;   //담는거 수량


}
