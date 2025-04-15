package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)  //롤백하지 않기
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository mr;

    @Test
    public void insert(){
        for(int i=1;i<=10;i++){
            Member m = new Member("hello"+i,"1234",10,"hello@test.com");
            mr.save(m);

        }
    }

    @Test
    public void update(){
        mr.update(new Member("hello","0000",20,"hello@tt.com"));
    }

    @Test
    public void select(){
        Member m=mr.find("hello");
        System.out.println(m);
    }

    @Test
    public void delete(){
        mr.delete("hello");
    }

    @Test
    public void count1(){
        long c= mr.count1();
        System.out.println("회원수"+ c);
    }

    @Test
    public void count2(){
        long c= mr.count2();
        System.out.println("회원수"+ c);
    }

    @Test
    public void list(){
        List<Member> list= mr.select();
        list.forEach(m->{
            System.out.println(m);
        });
    }

}
