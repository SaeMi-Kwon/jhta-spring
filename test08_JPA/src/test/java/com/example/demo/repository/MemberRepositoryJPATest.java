package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class MemberRepositoryJPATest {

    @Autowired
    private MemberRepositoryJPA mr;

    @Test
    public void insert(){
        Member m = mr.save(new Member("test","1111",19,"test@tt.com"));
        System.out.println("m ==>" + m);
    }

    @Test
    public void delete(){
        mr.deleteById("test");
    }

    @Test
    public void select(){
        Optional<Member> optionalMember = mr.findById("hello1");
        if(!optionalMember.isEmpty()){
            Member m = optionalMember.get();
            System.out.println(m);
        }else {
            System.out.println("데이터가 존재하지 않아요");
        }
    }
    
    @Test
    public void update(){
        Member m = mr.findById("hello1").get();
        m.setPassword("1111");
        m.setAge(30);
        m.setEmail("hello1@tt.com");
        System.out.println("수정완료");
    }

}
