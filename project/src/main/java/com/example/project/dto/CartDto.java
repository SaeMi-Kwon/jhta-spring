package com.example.project.dto;

import com.example.project.entity.Cart;
import com.example.project.entity.Member;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CartDto {
    private int cid;
    private int mid;  //member id
    private Date regdate;

    public CartDto(Cart cart){
        this.cid=cart.getCid();
        this.mid=cart.getMember().getMid();
        this.regdate=cart.getRegdate();
    }

    public Cart toEntity(Member member){
        return Cart.builder()
                .cid(cid)
                .member(member)
                .regdate(regdate)
                .build();
    }
}
