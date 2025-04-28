package com.example.project.service;

import com.example.project.dto.ProductDto;
import com.example.project.dto.ProductRequestDto;
import com.example.project.entity.Product;
import com.example.project.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Value("${file.path}")  // application.properties에 정의한 경로 사용 가능
    private String path;

    //상품삽입
    public ProductDto insert(ProductRequestDto productRequestDto) throws IOException {
        MultipartFile file = productRequestDto.getImg();
        File f = new File(path);
        if(!f.exists()) f.mkdir();

        String imgName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File fileSave = new File(path + File.separator + imgName);
        file.transferTo(fileSave);

        ProductDto dto = ProductDto.builder()
                .name(productRequestDto.getName())
                .amount(productRequestDto.getAmount())
                .price(productRequestDto.getPrice())
                .img(imgName)
                .build();

        return new ProductDto(productRepository.save(dto.toEntity()));
    }

    //상품리스트
    public List<ProductDto> list(){
        List<Product> list = productRepository.findAllByOrderByPidDesc();
        return list.stream().map(p->new ProductDto(p)).toList();
    }


    //상품 조회
    public ProductDto select(int pid){
        Optional<Product> dto = productRepository.findById(pid);

        if(dto.isPresent()){
            Product product = dto.get();
            return new ProductDto(product);
        }
        return null;
    }

    //상품 삭제
    public void delete(int pid){
        // 상품 조회
        ProductDto productDto = select(pid);
        String img = productDto.getImg();

        // 파일 삭제
        File file = new File(path + File.separator + img);
        if (file.exists()) {
            file.delete();
        }

        // DB 삭제
        productRepository.deleteById(pid);
    }

    //상품 수정
    public void update(ProductRequestDto productRequestDto) throws IOException {
        MultipartFile file = productRequestDto.getImg();
        int pid = productRequestDto.getPid();
        System.out.println("pid update ====> " + pid);

        //기존 상품 조회
        ProductDto dto = select(pid);
        if(dto == null){
            throw new IllegalArgumentException("상품이 존재하지 않습니다");
        }
        //기존 이미지 파일명 가져오기
        String oldImg = dto.getImg();

        //이미지 파일이 새로 업로드된 경우
        if(!file.isEmpty()){
            //기존파일 삭제
            File delf= new File(path + File.separator + oldImg);
            if(delf.exists()) delf.delete();

            //파일업로드
            String newImg= UUID.randomUUID() + "_" + file.getOriginalFilename();
            File newFile = new File(path + File.separator + newImg);
            file.transferTo(newFile);

            dto.setImg(newImg);
        }
        //dto담기
        dto.setName(productRequestDto.getName());
        dto.setPrice(productRequestDto.getPrice());
        dto.setAmount(productRequestDto.getAmount());

        //DB반영
        productRepository.save(dto.toEntity());
    }
}
