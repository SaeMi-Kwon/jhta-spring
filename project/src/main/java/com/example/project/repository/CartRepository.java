package com.example.project.repository;

import com.example.project.dto.CartProductDto;
import com.example.project.entity.Cart;
import com.example.project.entity.Member;
import com.example.project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Integer> {

    //Cart와 Product를 조인하여 CartProductDto로 데이터를 반환
    @Query("SELECT new com.example.project.dto.CartProductDto(c.cmid, c.member.mid, p.pid, p.name, p.img, p.price, c.quantity, c.quantity * p.price, c.added_at) " +
            "FROM Cart c JOIN c.product p WHERE c.member.mid = :mid")
    List<CartProductDto> findCartWithProductsByMid(int mid);

    //로그아웃 하면 장바구니 데이터 지우기
    //void deleteByMemberId(int mid);
}
