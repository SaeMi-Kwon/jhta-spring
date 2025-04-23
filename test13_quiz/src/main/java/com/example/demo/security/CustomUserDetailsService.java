package com.example.demo.security;

import com.example.demo.entity.BoardUser;
import com.example.demo.repository.BoardUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private BoardUserRepository boardUserRepository;

    //로그인 시 유저 정보를 어떻게 가져올지 정의
    @Override
    //로그인 시 username을 기반으로 사용자 검색 및 UserDetails 반환
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BoardUser boardUser = boardUserRepository.findById(username);
        if(boardUser==null){
            throw new UsernameNotFoundException("User not found");
        }
        UserDetails userDetails = new CustomUserDetails(boardUser);
        return userDetails;
    }
}
