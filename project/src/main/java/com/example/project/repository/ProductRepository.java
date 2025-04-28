package com.example.project.repository;

import com.example.project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    // pid 내림차순으로 정렬
    List<Product> findAllByOrderByPidDesc();
}
