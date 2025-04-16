package com.example.demo.entity;

/*
    학생번호,이름,전화번호 정보를 갖는 테이블을 작성하고 CRUD작업 해보기

    학생테이블을 참조하는 성적테이블을 만들고 데이터 저장후 학생 성적 조회하기
    성적테이블(성적번호,학생번호,과목명,점수,학기)
    1학기 성적조회(학생번호,이름,과목명,점수)
 */

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gnum;  //성적번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "snum")   //학생번호
    private Student student;

    private String subject;  //과목명
    private int score;  //점수
    private int semester;  //학기

}
