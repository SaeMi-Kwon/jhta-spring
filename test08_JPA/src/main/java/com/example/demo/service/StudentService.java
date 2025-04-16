package com.example.demo.service;

import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepositoryJPA;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepositoryJPA sr;

    public StudentDto insert(StudentDto studentDto){
        Student student = studentDto.toEntity();
        Student s =sr.save(student);
        StudentDto stuDto = new StudentDto(s);
        return stuDto;
    }

    public List<StudentDto> listAll(){
        List<Student> list = sr.findAll();
        return list.stream().map(s-> new StudentDto(s)).toList();
    }

    public StudentDto getInfo(int snum){
        Optional<Student> dto = sr.findById(snum);

        if(!dto.isEmpty()){
            Student student = dto.get();
            StudentDto studentDto = new StudentDto(student);
            return studentDto;
        }
        return null;
    }

    public StudentDto update(StudentDto studentDto){
        Optional<Student> student = sr.findById(1);

        if(!student.isEmpty()){
            Student s = student.get();
            s.setSnum(studentDto.getSnum());
            s.setName(studentDto.getName());
            s.setPhone(studentDto.getPhone());

            return new StudentDto(s);
        }

        return null;
    }

    public void delete(int snum){
        sr.deleteById(snum);
//        Student student = studentDto.toEntity();
//        sr.delete(student);
    }


}
