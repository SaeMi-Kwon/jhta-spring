package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //auto_increment로 자동 저장되도록
    private Long num;
    //LAZY : 이 정보를 조회(필요)할때만 받아오겠다 | EAGER : 즉시 정보를 받아오겠다
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    private Member member;
    private String title;
    @Lob
    private String content;
    @CreationTimestamp  //sysdate
    private Date regdate;

}
