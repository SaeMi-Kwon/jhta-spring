package com.example.demo.repository;

import com.example.demo.entity.MyUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class MyUserRepositoryTest {

    @Autowired
    private MyUserRepository mr;

    @Test
    public void insert(){
        //mr.insert(new MyUser(1,"홍길동","010-111-1111","종로"));
    }

    @Test
    public void select(){
        MyUser myUser=mr.select(1);
        System.out.println(myUser);
    }

    @Test
    public void update(){
        //mr.update(new MyUser(1,"김길동","010-000-0000","서울"));
    }

    @Test
    public void delete(){
        mr.delete(1);
    }

}
