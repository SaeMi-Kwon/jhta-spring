package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

    @Test
    public void selectList(){
        List<Member> m = mr.findAll();  //전체데이터 조회
        for(Member member : m){
            System.out.println(member);
        }
    }

    @Test
    public void selectOne(){
        Member m = mr.select("hello1");
        System.out.println(m);
    }

    @Test
    public void findMember(){
        Member m = mr.isMember("hello1", "1111");
        System.out.println(m);

    }

    @Test
    public void findAge(){
        List<Member> list = mr.findAgeMember(20);
        list.forEach(m->{
            System.out.println(m);
        });
    }

    @Test
    public void findIds(){
        List<String> ids=new ArrayList<>();
        ids.add("hello1");
        ids.add("hello5");
        ids.add("hello8");
        List<Member> list = mr.findMembers(ids);
        list.forEach(System.out::println);
    }

}
