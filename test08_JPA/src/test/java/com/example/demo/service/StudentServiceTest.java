package com.example.demo.service;

import com.example.demo.dto.StudentDto;
import com.example.demo.repository.StudentRepositoryJPA;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
@Transactional
@Commit
public class StudentServiceTest {

    @Autowired
    private StudentService service;

    @Test
    public void insert(){
        StudentDto dto=StudentDto.builder()
                .snum(3)
                .name("삼길동")
                .phone("010-333-3333")
                .build();

        StudentDto studentDto = service.insert(dto);
        System.out.println(studentDto);
    }

    @Test
    public void selectAll(){
        service.listAll().forEach(System.out::println);
    }

    @Test
    public void select(){
        StudentDto dto = service.getInfo(1);
        System.out.println(dto);
    }

    @Test
    public void update(){
        StudentDto dto = service.update(StudentDto.builder()
                        .snum(1)
                        .name("원길동")
                        .phone("010-000-000")
                        .build());

        System.out.println(dto);
    }

    @Test
    public void delete(){
        service.delete(3);


    }

}
