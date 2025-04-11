package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService service;

    @Test  //테스트는 리턴타입을 쓸수 없다
    public void insert(){
        int n=service.insert(new MemberDTO("test","1234","test@test.com",20,null));
        Assertions.assertEquals(n,1);
    }

}


