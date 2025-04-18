package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "grades")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int snum;
    private String name;
    private String phone;

    //mappedBy : 연관관계의 주체 설정(FK를 갖는 엔티티-자식테이블) 설정 -> 읽기 전용
    //CascadeType.ALL : 값을 추가하면 자식테이블에 같이 추가됨
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "student",cascade = CascadeType.ALL) //(student : 내가 주인 아님)
    private List<Grade> grades = new ArrayList<>();

    //snum는 auto_increment로 자동으로 들어가니깐 생성자 안해줘도 된다.
    public Student(String name,String phone){
        this.name=name;
        this.phone=phone;
    }
}
