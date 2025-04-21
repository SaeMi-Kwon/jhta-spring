package com.example.demo.dto;

import com.example.demo.entity.Comments;
import com.example.demo.entity.Movie;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentsDto {
    private Long cnum;
    private Long mnum;  //Movie mnum
    private String id;
    private String comments;

    public CommentsDto(Comments comments){
        this.cnum = comments.getCnum();
        this.mnum = comments.getMovie().getMnum();
        this.id = comments.getId();
        this.comments = comments.getComments();
    }

    public Comments toEntity(Movie movie){
        return Comments.builder()
                .cnum(cnum)
                .movie(movie)  //Movie객체를 전달해야함
                .id(id)
                .comments(comments)
                .build();
    }
}
