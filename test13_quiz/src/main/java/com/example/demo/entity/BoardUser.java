package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "boarduser")
public class BoardUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int buid;
    @Column(unique = true,nullable = false)
    private String id;
    @Column(nullable = false)
    private String pwd;
    private String email;
    private String phone;
    private String role;
    private String provider;
}
