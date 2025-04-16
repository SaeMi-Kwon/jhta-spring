package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryJPA extends JpaRepository<Member,String> {

    @Query("select m from Member m where m.id=:id")
    public Member select(@Param("id")String id);

    @Query("select m from Member m where m.age >= :age")
    public List<Member> findAgeMember(@Param("age")int age);

    //아이디와 비밀번호로 회원조회 하는 메소드
    @Query("select m from Member m where m.id = :id and m.password = :pwd")
    public Member isMember(@Param("id")String id, @Param("pwd")String pwd);

    @Query("select m from Member m where m.id in :ids")
    public List<Member> findMembers(@Param("ids")List<String> ids);
    
}
