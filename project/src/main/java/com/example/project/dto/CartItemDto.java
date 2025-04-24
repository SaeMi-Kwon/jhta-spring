package com.example.project.dto;


import com.example.project.entity.Cart;
import com.example.project.entity.CartItem;
import com.example.project.entity.Product;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CartItemDto {
    private int cmid;
    private int cid; //카트id
    private int pid;  //상품id
    private int quantity;   //담는거 수량
    private Date added_at;


    public CartItemDto(CartItem cartItem){
        this.cmid = cartItem.getCmid();
        this.cid = cartItem.getCart().getCid();
        this.pid = cartItem.getProduct().getPid();
        this.quantity = cartItem.getQuantity();
        this.added_at = cartItem.getAdded_at();
    }

    public CartItem toEntity(Cart cart, Product product){
        return CartItem.builder()
                .cmid(cmid)
                .cart(cart)
                .product(product)
                .quantity(quantity)
                .added_at(added_at)
                .build();
    }
}
