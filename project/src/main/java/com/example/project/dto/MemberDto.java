package com.example.project.dto;


import com.example.project.entity.Member;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MemberDto {
    private int mid;
    private String username;
    private String password;
    private String email;
    private String role;
    private Date regdate;

    public MemberDto(Member member){
        this.mid = member.getMid();
        this.username = member.getUsername();
        this.password = member.getPassword();
        this.email = member.getEmail();
        this.role= member.getRole();
        this.regdate = member.getRegdate();
    }

    public Member toEntity(){
        return Member.builder()
                .mid(mid)
                .username(username)
                .password(password)
                .email(email)
                .role(role)
                .regdate(regdate)
                .build();
    }

}
