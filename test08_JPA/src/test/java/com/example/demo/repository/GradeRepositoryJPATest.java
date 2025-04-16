package com.example.demo.repository;

import com.example.demo.entity.Grade;
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
public class GradeRepositoryJPATest {

    @Autowired
    private GradeRepositoryJPA gr;
    @Autowired
    private StudentRepositoryJPA sr;

    @Test
    public void save(){
        Optional<Student> stu = sr.findById(1);
        Student s = stu.get();

        Grade grade1 = gr.save(new Grade(0,s,"수학",80,1));
        Grade grade2 = gr.save(new Grade(0,s,"영어",75,1));
        Grade grade3 = gr.save(new Grade(0,s,"국어",65,1));
        Grade grade4 = gr.save(new Grade(0,s,"수학",90,2));
        Grade grade5 = gr.save(new Grade(0,s,"국어",85,2));
        Grade grade6 = gr.save(new Grade(0,s,"영어",60,2));
    }

    @Test
    public void findAll(){
        List<Grade> list = gr.findAll();
        list.forEach(System.out::println);
    }

    @Test
    public void semesterByFirst(){
        List<Grade> list = gr.semesterByFirst(2);
        list.forEach(System.out::println);
    }

    @Test
    public void findBySemester(){
        List<Grade> list = gr.findBySemester(2);
        list.forEach(System.out::println);
    }


    @Test
    public void delete(){
        gr.deleteById(1);
    }

    @Test
    public void update(){
        Grade g= gr.findById(2).get();
        g.setSubject("과학");
        g.setScore(8800);
        g.setSemester(2);
    }

    @Test
    public void findByScoreGreaterThanEqual(){
        List<Grade> list = gr.findByScoreGreaterThanEqual(70);
        list.forEach(System.out::println);
    }

}
