package com.example.demo.dto;

import com.example.demo.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieDto {
    private Long mnum;
    private String title;
    private String content;
    private String director;

    public MovieDto(Movie movie){
        this.mnum = movie.getMnum();
        this.title = movie.getTitle();
        this.content = movie.getContent();
        this.director = movie.getDirector();
    }

    public Movie toEntity(){
        Movie movie=Movie.builder()
                .mnum(mnum)
                .title(title)
                .content(content)
                .director(director)
                .build();

        return movie;
    }
}
