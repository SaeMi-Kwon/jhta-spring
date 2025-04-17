package com.example.demo.service;

import com.example.demo.dto.MemberDto;
import jakarta.persistence.Column;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
@Transactional
@Commit
public class MemberServiceTest {

    @Autowired
    private MemberService service;

    @Test
    public void insert(){
        MemberDto dto = MemberDto.builder()
                .id("bbbb")
                .age(10)
                .pwd("2222")
                .email("bbbb@tt.com")
                .build();

        MemberDto m = service.insert(dto);
        System.out.println(m);
    }

//    @Test
//    public void list(){
//        service.list().forEach(System.out::println);
//    }

    @Test
    public void update(){
        MemberDto dto = service.select("bbbb");
        System.out.println("변경전 dto ==> " + dto);

        MemberDto dto1 = service.update(new MemberDto("bbbb","4444","b@tt.com",22,null));
        System.out.println("변경후 dto ==> "+ dto1);
    }


}
