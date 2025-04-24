package com.example.demo;

import com.example.demo.model.Score;
import com.example.demo.model.Student;
import com.example.demo.repository.ScoreRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.ScoreCustomService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Commit;

import java.util.List;

@SpringBootTest
@Transactional
@Commit
public class RepositoryTest {

    @Autowired
    ScoreRepository scoreRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ScoreCustomService scoreCustomService;

    @Test
    public void save(){
        for(int i=1;i<=10;i++){
            Student student = new Student((long)i,"길동"+i,(int)(i*10));
            studentRepository.save(student);
            Score score = new Score((long)i,"kor"+2,i*10,(long)i);
            scoreRepository.save(score);
        }
        System.out.println("완료!");
    }

    @Test
    public void getInfo1(){
        Student s = scoreCustomService.getInfo1(1L);
        System.out.println("조회된 학생===> " + s);
    }

    @Test
    public void getInfo2(){
        List<Student> list = scoreCustomService.getInfo2("길동2");
        list.forEach(System.out::println);
    }

    @Test
    public void getInfo3(){
        List<Student> list = scoreCustomService.getInfo3(50);
        list.forEach(s-> System.out.println(s));
    }

    @Test
    public void getInfo4(){
        List<Student> list = scoreCustomService.getInfo4("길동1",8L);
        list.forEach(s -> System.out.println(s));
    }

    @Test
    public void getInfo4_1(){
        List<Student> list = scoreCustomService.getInfo4("길동1",null);
        list.forEach(s -> System.out.println(s));
    }

    @Test
    public void getJoin1(){
        scoreCustomService.getJoin1().forEach(System.out::println);
    }

    @Test
    public void getJoin2(){
        scoreCustomService.getJoin2().forEach(System.out::println);
    }

    @Test
    public void getJoin3(){
        scoreCustomService.getJoin3("길동3",5L).forEach(System.out::println);
    }

}
