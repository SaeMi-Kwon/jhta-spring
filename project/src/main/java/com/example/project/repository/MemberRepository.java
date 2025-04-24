package com.example.project.repository;


import com.example.project.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    Member findByUsername(String username);
}
