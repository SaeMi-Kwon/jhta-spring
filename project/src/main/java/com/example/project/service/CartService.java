package com.example.project.service;

import com.example.project.dto.CartDto;
import com.example.project.dto.CartProductDto;
import com.example.project.dto.CartRequestDto;
import com.example.project.dto.CartUpdateDto;
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
    public void insert(CartRequestDto cartRequestDto, int mid) {
        //상품과 사용자 정보 가져오기
        Product product = productRepository.findById(cartRequestDto.getPid())
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
        Member member = memberRepository.findById(mid)
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));

        Cart cart = Cart.builder()
                .product(product)
                .member(member)
                .quantity(cartRequestDto.getQuantity())
                .totalprice(product.getPrice() * cartRequestDto.getQuantity())
                .build();

        cartRepository.save(cart);
    }


    // 장바구니와 상품 정보를 조인하여 가져오기
    public List<CartProductDto> list(int mid){
        return cartRepository.findCartWithProductsByMid(mid);
    }

    //상품 조회
    public CartDto select(int cmid){
        Optional<Cart> cart = cartRepository.findById(cmid);
        if(cart.isPresent()){
            Cart c = cart.get();
            return new CartDto(c);
        }
        return null;
    }

    //장바구니 상품 수정하기
    public void update(CartUpdateDto updateDto) {
        Cart cart = cartRepository.findById(updateDto.getCmid())
                .orElseThrow(() -> new IllegalArgumentException("장바구니 항목 없음"));

        int total = updateDto.getQuantity() * updateDto.getPrice();
        cart.setQuantity(updateDto.getQuantity());
        cart.setTotalprice(total);
    }


    //장바구니 상품 삭제하기
    public void deleteItems(List<Integer> cmidList) {
        //이벤트처리 사용 못하는 대신 성능면에서 빠름
        cartRepository.deleteAllByIdInBatch(cmidList);
    }
}
