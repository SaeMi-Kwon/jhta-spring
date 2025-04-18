package com.example.demo.repository;

import com.example.demo.entity.Grade;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade,Integer> {

    @Query("select g from Grade g join g.student s")
    List<Grade> list1();

    @Query("select g from Grade g join fetch g.student s") //조인패치 - 무조건 데이터를 조회함
    List<Grade> list2();

    //@EntityGraph : fetch조인을 한다
    @EntityGraph(attributePaths = {"student"})
    @Query("select g from Grade g")
    List<Grade> list3();
}
