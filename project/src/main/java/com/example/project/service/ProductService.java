package com.example.project.service;

import com.example.project.dto.ProductDto;
import com.example.project.entity.Product;
import com.example.project.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository pr;

    //상품삽입
    public ProductDto insert(ProductDto productDto){
        return new ProductDto(pr.save(productDto.toEntity()));
    }

    //상품리스트
    public List<ProductDto> list(){
        List<Product> list = pr.findAll();
        return list.stream().map(p->new ProductDto(p)).toList();
    }



}
