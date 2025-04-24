package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
//조인해서 담기위한 클래스 (조인클래스)
public class StudentScoreResponse {
    private Long studentId;
    private String name;
    private Integer age;
    private Long scoreId;
    private String subject;
    private Integer score;
}
