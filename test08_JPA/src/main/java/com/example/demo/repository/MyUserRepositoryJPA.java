package com.example.demo.repository;

import com.example.demo.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepositoryJPA extends JpaRepository<MyUser,Integer> {

}
