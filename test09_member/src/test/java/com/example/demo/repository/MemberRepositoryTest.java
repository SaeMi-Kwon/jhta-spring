package com.example.demo.repository;

import com.example.demo.entity.Member;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.util.List;

@SpringBootTest
@Transactional
@Commit
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository mr;

    @Test
    public void list1(){
        List<Member> list = mr.list1(0,5);
        list.forEach(System.out::println);
    }

    @Test
    public void list2(){
        List<Member> list = mr.list2(5,5);
        list.forEach(System.out::println);
    }

//    @Test
//    public void list3(){
//        //public Page<Member> findAll(Pageable pageable);
//        //pageNumber는 0부터시작
//        //PageRequest pageable=PageRequest.of(1,3);  //2페이지부터 3개를 꺼내옴
//        //나이를 기준으로 내림차순정렬
//        PageRequest pageable=PageRequest.of(0,3,Sort.by("age").descending());
//        Page<Member> page = mr.findAll(pageable);
//        System.out.println("전체페이지 수:" + page.getTotalPages());
//        List<Member> list = page.getContent();
//        list.forEach(System.out::println);
//    }

    @Test
    public void increAge(){
        int n = mr.update("aaaa");
        System.out.println("n ==> " + n);
    }
}
