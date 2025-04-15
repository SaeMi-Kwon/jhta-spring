package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "myuser")
public class MyUser {  //카멜표기법
    @Id
    private int num;
    private String name;
    private String phone;
    private String addr;
}
