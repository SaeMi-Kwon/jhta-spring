package com.example.demo.repository;

import com.example.demo.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepositoryJPA extends JpaRepository<Grade,Integer> {
    //1학기 성적 조회(학생번호,이름,과목명,점수)
    //방법1
    @Query("select g from Grade g join g.student s where g.semester=:semester")
    public List<Grade> semesterByFirst(@Param("semester")int semester);

    //방법2
    List<Grade> findBySemester(int semester);

    //점수가 70점 이상인 성적 조회
    List<Grade> findByScoreGreaterThanEqual(int score);

}
