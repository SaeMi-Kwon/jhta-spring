package com.example.demo.repository;

import com.example.demo.dto.BoardDto;
import com.example.demo.entity.Board;
import com.example.demo.entity.Member;
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
public class BoardRepositoryJPATest {

    @Autowired
    private BoardRepositoryJPA br;
    @Autowired
    private MemberRepositoryJPA mr;

    @Test
    public void save(){
        mr.save(new Member("hello","0000",25,"hello@tt.com"));
        mr.save(new Member("aaaa","2222",20,"aaaa@tt.com"));

        Optional<Member> mem = mr.findById("hello");
        Member m = mem.get();
        for(int i=1;i<=10;i++){
            br.save(new Board(null,m,"테스트"+ i,"좋아요" + i,null));
        }

    }

    @Test
    public void list(){
        List<Board> blist = br.findAll();
        blist.forEach(e-> {
            System.out.println("글번호 : "+ e.getNum());
            System.out.println("작성자 : "+ e.getMember().getId());
            System.out.println("작성자 이메일 : "+ e.getMember().getEmail());
            System.out.println("제목 : "+ e.getTitle());
            System.out.println("내용 : "+ e.getContent());
            System.out.println("작성일 : "+ e.getRegdate());

        });
    }

    @Test
    public void list1(){
        List<Board> list = br.list1();
        list.forEach(e-> {
            System.out.println("글번호 : " + e.getNum());
            System.out.println("작성자 : " + e.getMember().getId());
            System.out.println("비밀번호 : " + e.getMember().getPassword());
            System.out.println("제목 : " + e.getTitle());
            System.out.println("내용 : " + e.getContent());
            System.out.println("작성일 : " + e.getRegdate());
        });
    }

    @Test
    public void list2(){
        List<Board> list = br.list2("aaaa");
        list.forEach(System.out::println);
    }

    @Test
    public void list3(){
        List<BoardDto> list= br.list3();
        for(BoardDto boardDto : list){
            System.out.println(boardDto);
        }
    }

    @Test
    public void delete(){
        br.deleteById(1L);
    }

    @Test
    public void update(){
        Board b=br.findById(2L).get();
        b.setTitle("test...");
        b.setContent("teetets!!");
        //br.save(b);
    }


}
