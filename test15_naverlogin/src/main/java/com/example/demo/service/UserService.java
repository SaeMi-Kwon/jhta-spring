package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    //회원가입(저장)
    public void save(User user){
        String password= user.getPassword();
        //패스워드 암호화 하기
        String encodedPassword=passwordEncoder.encode(password);
        user.setPassword(encodedPassword);  //패스워드 암호화 된걸로 저장
        userRepository.save(user);
    }

    //로그인
    public boolean isMember(String username,String password){
        //DB에 암호화되어져서 저장된 비밀번호 얻어오기
        String encodedPassword=userRepository.findByUsername(username).getPassword();
        //matches() : 사용자가 입력한 비밀번호(암호화 안된 비밀번호)와
        // 암호화된 비밀번호가 같은지 검사하기
        if(passwordEncoder.matches(password,encodedPassword)){
            return true;
        }else {
            return false;
        }
    }
}

