package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity  //이 클래스가 테이블과 매핑이 된다는 것을 알려줌
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Table(name="Members")  //테이블명 지정가능
public class Member {
    @Id   //PK가 되는 컬럼을 의미
    private String id;
    
    @Column(name="pwd")   //컬럼명 지정가능
    private String password;
    private int age;
    private String email;
}
