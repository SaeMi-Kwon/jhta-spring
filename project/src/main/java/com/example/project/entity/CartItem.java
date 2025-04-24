package com.example.project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "cartitem")
public class CartItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cmid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cid")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid")
    private Product product;

    private int quantity;   //담는거 수량

    @CreationTimestamp
    private Date added_at;
}
