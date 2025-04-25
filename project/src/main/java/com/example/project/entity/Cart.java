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
public class Cart {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cmid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mid")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid")
    private Product product;

    private int quantity;   //담는거 수량
    private int totalprice;  //총 가격

    @CreationTimestamp
    private Date added_at;

    @PrePersist
    public void prePersist() {
        // totalprice가 null이면 계산해서 넣도록
        if (totalprice == 0) {
            totalprice = quantity * product.getPrice();
        }
    }

}
