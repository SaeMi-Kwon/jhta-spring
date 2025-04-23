package com.example.demo.security;


import com.example.demo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

//Spring Security는 인증(Authentication)과 인가(Authorization)를 담당하는 보안 프레임워크
//로그인 과정에서 사용자의 정보를 확인하고, 권한을 체크하는데 이 때 사용하는 객체가 바로 UserDetails


// 추상메소드 오버라이딩 ctrl + i
//사용자 클래스(User)는 보통 Spring Security가 원하는 형식이 아니기 때문에,
//그 사이를 연결해주는 "어댑터" 역할을 하는 게 이 CustomUserDetails 클래스
public class CustomUserDetails implements UserDetails, OAuth2User { //우리가 만든 User 객체를 UserDetails 인터페이스에 맞춰 감싸주는 역할
    private User user;
    private Map<String,Object> attributes;

    public CustomUserDetails(User user,Map<String,Object> attributes){
        this.user=user;
        this.attributes=attributes;
    }

    public CustomUserDetails(User user){
        this.user=user;
    }

    //사용자가 가지고 있는 권한(ROLE반환)
    //Spring Security는 ROLE_USER, ROLE_ADMIN 같은 권한 정보를 기반으로 접근 제어
    @Override
    //<? extends GrantedAuthority> : GrantedAuthority타입이거나 GrantedAuthority를 상속받았거나
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    //사용자 비밀번호 반환
    //로그인할 때 입력한 비밀번호와 비교할, DB에 저장된 비밀번호를 반환
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    //사용자 이름 반환
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getName() {
        return user.getUsername();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
