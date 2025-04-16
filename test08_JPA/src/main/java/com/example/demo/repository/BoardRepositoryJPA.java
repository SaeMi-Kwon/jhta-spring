package com.example.demo.repository;

import com.example.demo.dto.BoardDto;
import com.example.demo.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepositoryJPA extends JpaRepository<Board,Long> {

    // private Member member에 전부 담겨서 나오니깐 m.password는 의미는 없음
    @Query("select b,m.password from Board b join b.member m")
    List<Board> list1();

    @Query("select b from Board b join b.member m where m.id = :id")
    List<Board> list2(@Param("id")String id);

    @Query("select new com.example.demo.dto.BoardDto(b.num,m.email,b.title,b.content)" +
            "from Board b join b.member m")
    List<BoardDto> list3();

}
