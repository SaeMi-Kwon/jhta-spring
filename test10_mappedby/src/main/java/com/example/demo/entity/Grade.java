package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "student")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gnum;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "snum")
    private Student student;
    private String subject;
    private int score;
}
