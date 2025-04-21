package com.example.demo.service;

import com.example.demo.dto.CommentsDto;
import com.example.demo.dto.MovieDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
@Transactional
@Commit
public class MovieServiceTest {

    @Autowired private MovieService movieService;
    @Autowired private CommentsService commentService;

    @Test
    public void movieInsert(){
        movieService.insert(new MovieDto(null,"스프링부트","재미난이야기","이감독"));
        movieService.insert(new MovieDto(null,"무서운영화","무서운이야기","최감독"));
    }

    @Test
    public void commInsert(){
        for(int i=1;i<=12;i++){
            commentService.insert(new CommentsDto(null,1L,"멍멍이"+i,"흥미진진해요!!.."+i));
            //commentService.insert(new CommentsDto(null,2L,"야옹이"+i,"무서워요.."+i));
        }
    }
}
