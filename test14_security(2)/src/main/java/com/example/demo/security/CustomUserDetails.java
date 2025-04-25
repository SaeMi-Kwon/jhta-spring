package com.example.demo.security;

import com.example.demo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
// 추상메소드 오버라이딩 ctrl + i
public class CustomUserDetails implements UserDetails {
    private User user;
    public CustomUserDetails(User user){
        this.user=user;
    }
    //사용자가 가지고 있는 권한(ROLE반환)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }
    //사용자 비밀번호 반환
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    //사용자 이름 반환
    @Override
    public String getUsername() {
        return user.getUsername();
    }
}









