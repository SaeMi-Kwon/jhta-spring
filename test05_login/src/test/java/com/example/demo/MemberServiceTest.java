package com.example.demo;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;


@SpringBootTest
@Slf4j
public class MemberServiceTest {

    @Autowired
    private MemberService service;

    @Test
    public void insert(){
        int n=service.insert(new MemberDTO("zxcv","5555","zxcv@test.com",28,null));
        //System.out.println("n:" + n);
        //log.debug("n:" + n);  //사용할수 있지만 권장하지 않음
        log.warn("n:{}",n);


        //Assertions.assertEquals(n,1);
    }

    @Test
    public void isMember(){
        HashMap<String,Object> map =new HashMap<>();
        map.put("id","asdf");
        map.put("pwd","95132");
        MemberDTO dto=service.isMember(map);
        log.warn("dto:{}",dto);
    }
}
