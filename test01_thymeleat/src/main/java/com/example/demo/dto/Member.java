package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {
    private String id;
    private String pwd;
    private String email;
    private boolean minor;  //미성년자

}
