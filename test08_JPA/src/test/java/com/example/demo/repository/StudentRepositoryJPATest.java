package com.example.demo.repository;

import com.example.demo.entity.Student;
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
public class StudentRepositoryJPATest {

    @Autowired
    private StudentRepositoryJPA sr;

    @Test
    public void save(){
        Student student = sr.save(new Student(1,"김길동","010-111-1111"));
        System.out.println(student);
    }

    @Test
    public void findById(){
        Optional<Student> stu = sr.findById(1);
        Student s = stu.get();
        System.out.println(s);
    }

    @Test
    public void update(){
        Optional<Student> stu = sr.findById(1);
        Student s = stu.get();
        s.setName("김길동");
        s.setPhone("010-000-0000");
    }

    @Test
    public void findAll(){
        List<Student> list = sr.findAll();
        list.forEach(System.out::println);
    }

    @Test
    public void delete(){
        Optional<Student> stu = sr.findById(1);
        Student s = stu.get();
        sr.delete(s);
    }

    @Test
    public void findByName(){
        List<Student> list = sr.findByName("김길동");
        list.forEach(System.out::println);

    }

    @Test
    public void findByNameOrPhone(){
        List<Student> s = sr.findByNameOrPhone("삼길동","010-111-1111");
        s.forEach(System.out::println);
    }

    @Test
    public void findByNameContains(){
        List<Student> s = sr.findByNameContaining("길동");
        s.forEach(System.out::println);
    }

}
