package com.example.project.controller;

import com.example.project.dto.CartDto;
import com.example.project.dto.CartProductDto;
import com.example.project.dto.CartRequestDto;
import com.example.project.dto.ProductDto;
import com.example.project.security.CustomMemberDetails;
import com.example.project.service.CartService;
import com.example.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody CartRequestDto cartRequestDto,
                                            @AuthenticationPrincipal CustomMemberDetails customMemberDetails) {
        try {
            ProductDto productDto = productService.select(cartRequestDto.getPid());
            int mid = customMemberDetails.getMid();

            CartDto cartDto = CartDto.builder()
                    .mid(mid)
                    .pid(productDto.getPid())
                    .quantity(cartRequestDto.getQuantity())
                    .build();

            cartService.insert(cartDto);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("fail", HttpStatus.EXPECTATION_FAILED);
        }
    }

    //장바구니 리스트 반환 (상품과 사용자 정보 포함)
    @GetMapping("/list")
    public List<CartProductDto> getCartList(@AuthenticationPrincipal CustomMemberDetails customMemberDetails) {
        // 로그인된 사용자 정보는 customMemberDetails로 자동 주입
        int memberId = customMemberDetails.getMid();

        // 장바구니 목록을 반환
        return cartService.list(memberId);
    }


    //@DeleteMapping("/del")

}
