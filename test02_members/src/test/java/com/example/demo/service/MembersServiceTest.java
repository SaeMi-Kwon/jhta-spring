package com.example.demo.service;

import com.example.demo.dto.MembersDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MembersServiceTest {

    @Autowired
    MembersService service;

    @Test
    public void insert(){
        MembersDTO dto=new MembersDTO(4,"사길동","010-444-4444","종로",null);
        int n=service.insert(dto);
        System.out.println("n ==> " + n);
    }

    @Test
    public void update(){
        MembersDTO dto=new MembersDTO(4,"수정길","010-444-1111","대전",null);
        int n=service.update(dto);
        Assertions.assertEquals(n,1);
    }

    @Test
    public void delete(){
        int n=service.delete(4);
        Assertions.assertEquals(n,1);
    }

    @Test
    public void select(){
        MembersDTO dto=service.getinfo(1);
        System.out.println(dto);
    }

    @Test
    public void selectAll(){
        List<MembersDTO> list=service.selectAll();
        list.forEach(m->{
            System.out.println(m);
        });
    }

}
