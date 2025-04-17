package com.example.demo.service;

import com.example.demo.dto.BoardDto;
import com.example.demo.dto.PageResultDto;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
@Transactional
@Commit
public class BoardServiceTest {

    @Autowired
    private BoardService service;

    @Autowired
    private BoardRepository br;

    @Autowired
    private MemberRepository mr;


    @Test
    public void insert1(){
        BoardDto dto = service.insert(BoardDto.builder()
                        .id("iiiiii")
                        .title("test")
                        .content("dfadsf")
                        .build());
        System.out.println(dto);
    }

    @Test
    public void pageList(){
        PageRequest pageable=PageRequest.of(0,5);
        Page<Board> page = br.findAll(pageable);
        System.out.println("페이지 전체 갯수: " + page.getTotalPages());
        System.out.println("페이지 번호: " + page.getNumber());
        page.getContent().forEach(System.out::println);
    }

    @Test
    public void list(){
        PageRequest pageable=PageRequest.of(0,5);
        PageResultDto dto=service.list(pageable);
        System.out.println(dto);
    }

    @Test
    public void select(){
        BoardDto dto = service.select(14L);
        System.out.println("조회 ==>" + dto);
    }

    @Test
    public void update(){
        BoardDto dto = service.update(new BoardDto(14L,null,"수정테스트","수정테스트트트ㅡ트트",null));
        System.out.println("수정후==>" + dto);
    }

    @Test
    public void delete(){
        service.delete(10L);
    }

}
