package com.example.demo.security;


import com.example.demo.entity.BoardUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


public class CustomUserDetails implements UserDetails, OAuth2User { //우리가 만든 User 객체를 UserDetails 인터페이스에 맞춰 감싸주는 역할
    private BoardUser boardUser;
    private Map<String,Object> attributes;

    public CustomUserDetails(BoardUser boardUser,Map<String,Object> attributes){
        this.boardUser=boardUser;
        this.attributes=attributes;
    }

    public CustomUserDetails(BoardUser boardUser){
        this.boardUser=boardUser;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(boardUser.getRole()));
        return authorities;
    }

    //사용자 비밀번호 반환
    //로그인할 때 입력한 비밀번호와 비교할, DB에 저장된 비밀번호를 반환
    @Override
    public String getPassword() {
        return boardUser.getPwd();
    }

    //사용자 이름 반환
    @Override
    public String getUsername() {
        return boardUser.getId();
    }

    @Override
    public String getName() {
        return boardUser.getId();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
