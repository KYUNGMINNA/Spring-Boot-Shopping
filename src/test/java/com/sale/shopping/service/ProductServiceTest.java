package com.sale.shopping.service;


import com.sale.shopping.model.dto.ProductRequestDTO;
import com.sale.shopping.model.entity.Product;
import com.sale.shopping.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    private final Logger log= LoggerFactory.getLogger(ProductServiceTest.class);


    @InjectMocks //인터페이스 보다는 객체를 리턴해야함  -->인터페이스 쓰려면 , 해당 인터페이스를 구현한 클래스
    //객체 생성해야 하는 과정 필요 !
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;


    @Test
    @Transactional(rollbackFor = RuntimeException.class)
    @DisplayName("서비스 계층 상품 등록 테스트 ")
    public void insertTest(){
        //given
        ProductRequestDTO productRequestDTO=ProductRequestDTO.builder()
                .productTitle("제목")
                .productImage("이미지위치")
                .productContent("내용")
                .productPrice(987654321)
                .productCount(1000)
                .build();

        //stub
        when(productRepository.save(any())).thenReturn(productRequestDTO.toEntity());

        //when
        Product productPS=productRepository.save(productRequestDTO.toEntity());

        //then
        assertEquals(productPS.getProductTitle(),productRequestDTO.getProductTitle());
        assertEquals(productPS.getProductImage(),productRequestDTO.getProductImage());
        assertEquals(productPS.getProductContent(),productRequestDTO.getProductContent());
        assertEquals(productPS.getProductPrice(),productRequestDTO.getProductPrice());
        assertEquals(productPS.getProductCount(),productRequestDTO.getProductCount());

    }

    @Test
    @Transactional(rollbackFor = RuntimeException.class)
    @DisplayName("서비스 계층 상품 삭제 테스트 ")
    public void deleteTest(){
        //given
        Integer id=1;
        ProductRequestDTO productRequestDTO=ProductRequestDTO.builder()
                .productTitle("제목")
                .productImage("이미지위치")
                .productContent("내용")
                .productPrice(987654321)
                .productCount(1000)
                .build();

        //stub
        when(productRepository.save(any())).thenReturn(productRequestDTO.toEntity());

        //when
        Product product=productRepository.save(productRequestDTO.toEntity());
        productRepository.deleteById(1);

        //then
        assertFalse(productRepository.findById(id).isPresent());
    }


}
