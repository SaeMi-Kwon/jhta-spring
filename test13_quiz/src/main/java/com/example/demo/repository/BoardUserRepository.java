package com.example.demo.repository;

import com.example.demo.entity.BoardUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardUserRepository extends JpaRepository<BoardUser,Integer> {
    BoardUser findById(String id);
}
