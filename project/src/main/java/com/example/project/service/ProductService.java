package com.example.project.service;

import com.example.project.dto.ProductDto;
import com.example.project.entity.Product;
import com.example.project.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    //상품삽입
    public ProductDto insert(ProductDto productDto){
        return new ProductDto(productRepository.save(productDto.toEntity()));
    }

    //상품리스트
    public List<ProductDto> list(){
        List<Product> list = productRepository.findAll();
        return list.stream().map(p->new ProductDto(p)).toList();
    }


    //상품 조회
    public ProductDto select(int pid){
        Optional<Product> dto =productRepository.findById(pid);

        if(dto.isPresent()){
            Product product = dto.get();
            return new ProductDto(product);
        }
        return null;
    }


}
