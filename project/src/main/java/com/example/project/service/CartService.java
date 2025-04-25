package com.example.project.service;

import com.example.project.dto.CartDto;
import com.example.project.dto.CartProductDto;
import com.example.project.entity.Cart;
import com.example.project.entity.Member;
import com.example.project.entity.Product;
import com.example.project.repository.CartRepository;
import com.example.project.repository.MemberRepository;
import com.example.project.repository.ProductRepository;
import com.example.project.security.CustomMemberDetails;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    //장바구니 추가
    public void insert(CartDto cartDto){
        //상품과 사용자 정보 가져오기
        Optional<Product> product = productRepository.findById(cartDto.getPid());
        Optional<Member> member = memberRepository.findById(cartDto.getMid());

        if(product.isPresent() && member.isPresent()){
            Product p = product.get();
            Member m = member.get();
            Cart cart = cartDto.toEntity(m,p);
            cartRepository.save(cart);
        }
        System.out.println("장바구니 저장 실패!");

    }

    // 장바구니와 상품 정보를 조인하여 가져오기
    public List<CartProductDto> list(int mid){
        return cartRepository.findCartWithProductsByMid(mid);
    }

}
