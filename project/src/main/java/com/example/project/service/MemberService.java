package com.example.project.service;

import com.example.project.dto.MemberDto;
import com.example.project.entity.Member;
import com.example.project.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository mr;

    //회원가입
    public void insert(MemberDto memberDto){
        Member mm = memberDto.toEntity();
        String pwd = mm.getPassword();
        String encodedPwd=passwordEncoder.encode(pwd);
        mm.setPassword(encodedPwd);
        mr.save(mm);
    }

    //로그인
    public boolean isMember(String username,String password){
        String encodedPwd=mr.findByUsername(username).getPassword();
        if(passwordEncoder.matches(password,encodedPwd)){
            return true;
        }
        return false;
    }

}
