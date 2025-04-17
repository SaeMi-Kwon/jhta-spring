package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(exclude = {"member"})  //ToString메소드 오버라이딩에 member는 제외하기
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Member member;
    private String title;
    @Lob
    private String content;
    @CreationTimestamp //sydate
    private LocalDateTime regdate;
}
