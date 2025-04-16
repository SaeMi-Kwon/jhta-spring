package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepositoryJPA extends JpaRepository<Student,Integer> {

    List<Student> findByName(String name);

    List<Student> findByNameOrPhone(String name,String phone);

    List<Student> findByNameContaining(String name);
}
