package com.example.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    private String name;
    private int price;
    private int amount;  //재고수량
    private String img;  //이미지
    @CreationTimestamp
    private Date regdate;

}
