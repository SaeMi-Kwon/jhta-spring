package com.example.demo.repository;

import com.example.demo.entity.MyUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class MyUserRepositoryJPATest {

    @Autowired
    private MyUserRepositoryJPA mr;

    @Test
    public void insert(){
        for(int i=1;i<=10;i++){
            MyUser mu=mr.save(new MyUser(i,"홍길동","010-222-2222","강남"));
            System.out.println("mu ==> " + mu);
        }
    }

    @Test
    public void select(){
        Optional<MyUser> optionalMyUser = mr.findById(2);

        if(!optionalMyUser.isEmpty()){
            MyUser myUser = optionalMyUser.get();
            System.out.println(myUser);
        }else {
            System.out.println("데이터가 존재하지 않아요");
        }
    }

    @Test
    public void update(){
        Optional<MyUser> optionalMyUser = mr.findById(2);

        if(!optionalMyUser.isEmpty()) {
            MyUser mu = optionalMyUser.get();
            mu.setName("길동이");
            mu.setPhone("010-202-2020");
            mu.setAddr("대구");

        }else {
            System.out.println("수정실패!");
        }
    }

    @Test
    public void delete(){
        mr.deleteById(2);
    }

    @Test
    public void selectAll(){
        List<MyUser> list =mr.findAll();
        list.forEach(mu->{
            System.out.println(mu);
        });
    }

}
