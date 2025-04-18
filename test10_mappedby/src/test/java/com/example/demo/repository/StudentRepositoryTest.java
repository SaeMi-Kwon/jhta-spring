package com.example.demo.repository;

import com.example.demo.entity.Grade;
import com.example.demo.entity.Student;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Commit;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@Commit
public class StudentRepositoryTest {

    @Autowired private StudentRepository sr;
    @Autowired private GradeRepository gr;

    @Test
    public void save(){
        Student s = new Student("박길동","010-555-5555");
        sr.save(s);
        Student s1 = new Student("최길동","010-666-6666");
        sr.save(s1);
        Student s3 = new Student("홍길동","010-111-1111");
        sr.save(s3);
        Student s4 = new Student("이길동","010-222-2222");
        sr.save(s4);
    }

    @Test
    public void saveGrade(){
        Student s=sr.findById(1).get();
        gr.save(new Grade(0,s,"국어",100));
        gr.save(new Grade(0,s,"영어",80));

        Student s1=sr.findById(2).get();
        gr.save(new Grade(0,s1,"국어",95));
        gr.save(new Grade(0,s1,"영어",60));

        Student s2=sr.findById(3).get();
        gr.save(new Grade(0,s2,"국어",65));
        gr.save(new Grade(0,s2,"영어",90));
    }

    @Test
    public void printAll(){
        List<Student> slist=sr.findAll();
        for(Student s : slist){
            System.out.println("학생번호:" + s.getSnum());
            System.out.println("학생이름:" + s.getName());
            System.out.println("<< 성적표 >>");
            List<Grade> glist = s.getGrades();
            for(Grade g : glist){
                System.out.println("과목명:" + g.getSubject());
                System.out.println("점수:" + g.getScore());
            }
            System.out.println();
        }
    }

    @Test
    public void save1(){
        Student s=new Student("담길동","010-737-7373");
        ArrayList<Grade> list=(ArrayList<Grade>) s.getGrades();
        list.add(new Grade(0,s,"사회",50));
        list.add(new Grade(0,s,"과학",78));
        //CascadeType.ALL옵션을 지정했기때문에 Grade테이블에도 값이 같이 등록된다.
        sr.save(s);
    }
    
    @Test
    public void printGrade(){
        //List<Grade> grade = gr.list1();
        //List<Grade> grade = gr.list2();
        List<Grade> grade = gr.list3();
        for(Grade g : grade){
            System.out.println("성적번호:" + g.getGnum());
            System.out.println("학생번호:" + g.getStudent().getSnum());
            System.out.println("과목명:" + g.getSubject());
            System.out.println("점수:" + g.getScore());
            System.out.println();
        }
    }

}
