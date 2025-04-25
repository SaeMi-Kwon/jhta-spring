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
public class CartDto {
    private int cmid;
    private int mid; //사용자 id
    private int pid;  //상품id
    private int quantity;   //담는거 수량
    private int totalprice;  //총 가격
    private Date added_at;


    public CartDto(Cart cart){
        this.cmid = cart.getCmid();
        this.mid = cart.getMember().getMid();
        this.pid = cart.getProduct().getPid();
        this.quantity = cart.getQuantity();
        this.totalprice = cart.getQuantity() * cart.getProduct().getPrice();
        this.added_at = cart.getAdded_at();
    }

    public Cart toEntity(Member member, Product product){
        return Cart.builder()
                .cmid(cmid)
                .member(member)
                .product(product)
                .quantity(quantity)
                .totalprice(quantity * product.getPrice())
                .added_at(added_at)
                .build();
    }
}
