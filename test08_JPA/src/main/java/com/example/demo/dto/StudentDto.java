package com.example.demo.dto;

import com.example.demo.entity.Member;
import com.example.demo.entity.Student;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudentDto {
    private int snum;
    private String name;
    private String phone;

    public StudentDto(Student stu){
        this.snum = stu.getSnum();
        this.name = stu.getName();
        this.phone = stu.getPhone();
    } 
    
    public Student toEntity(){
        Student student = Student.builder()
                .snum(snum)
                .name(name)
                .phone(phone)
                .build();
        return student;
    }
}
