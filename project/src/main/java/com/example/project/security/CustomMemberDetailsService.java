package com.example.project.security;

import com.example.project.entity.Member;
import com.example.project.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomMemberDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository mr;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //엔티티(Entity)를 직접 사용
        Member member = mr.findByUsername(username);
        if(member==null){
            throw new UsernameNotFoundException("존재하지 않는 아이디 또는 비밀번호 입니다");
        }
        UserDetails details = new CustomMemberDetails(member);
        return details;
    }
}
