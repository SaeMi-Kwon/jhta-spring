package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public void save(User user){
        String password=user.getPassword();
        //패스워드 암호화 하기
        String encodedPassword=passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
    public boolean isMember(String username,String password){
        //DB에 암호화되어져서 저장된 비밀번호 얻어오기
        String encodedPassword=userRepository.findByUsername(username).getPassword();
        //matches메소드: 사용자가 입력한 비밀번호(암호화 안된 비밀번호)와
        // 암호화된 비밀번호가 같은지 검사하기
        if(passwordEncoder.matches(password,encodedPassword)){
            return true;
        }else{
            return false;
        }
   }
}











