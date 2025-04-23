package com.example.demo.service;

import com.example.demo.entity.BoardUser;
import com.example.demo.repository.BoardUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardUserService {

    private final PasswordEncoder passwordEncoder;
    private final BoardUserRepository boardUserRepository;

    public void save(BoardUser boardUser){
        String pwd = boardUser.getPwd();
        //패스워드 암호화 하기
        String encodedPassword=passwordEncoder.encode(pwd);
        boardUser.setPwd(encodedPassword);  //패스워드 암호화 된걸로 저장
        boardUserRepository.save(boardUser);
    }

    public boolean isMember(String username,String password){
        //DB에 암호화되어져서 저장된 비밀번호 얻어오기
        String encodedPassword=boardUserRepository.findById(username).getPwd();
        //matches() : 사용자가 입력한 비밀번호(암호화 안된 비밀번호)와
        // 암호화된 비밀번호가 같은지 검사하기
        if(passwordEncoder.matches(password,encodedPassword)){
            return true;
        }else {
            return false;
        }
    }
}

