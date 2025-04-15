package com.example.demo.repository;

import com.example.demo.entity.MyUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MyUserRepository {

    @PersistenceContext
    private EntityManager em;

    public void insert(MyUser myUser){
        em.persist(myUser);
    }

    public MyUser select(int num){
        MyUser myUser=em.find(MyUser.class,num);
        return myUser;
    }

    public MyUser update(MyUser myUser){
         MyUser user=em.find(MyUser.class,myUser.getNum());
         user.setName(myUser.getName());
         user.setPhone(myUser.getPhone());
         user.setAddr(myUser.getAddr());
         return user;
    }

    public void delete(int num){
        MyUser user=em.find(MyUser.class,num);
        em.remove(user);
    }


}
