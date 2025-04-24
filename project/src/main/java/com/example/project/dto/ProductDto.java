package com.example.project.dto;

import com.example.project.entity.Product;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProductDto {
    private int pid;
    private String name;
    private int price;
    private int amount;  //재고수량
    private String img;  //이미지
    private Date regdate;

    public ProductDto(Product product){
        this.pid = product.getPid();
        this.name = product.getName();
        this.price = product.getPrice();
        this.amount = product.getAmount();
        this.img = product.getImg();
        this.regdate = product.getRegdate();
    }

    public Product toEntity(){
        return Product.builder()
                .pid(pid)
                .name(name)
                .price(price)
                .amount(amount)
                .img(img)
                .regdate(regdate)
                .build();
    }
}
