package com.sale.shopping.service;


import com.sale.shopping.model.dto.ProductRequestDTO;
import com.sale.shopping.model.dto.ProductResponseDTO;
import com.sale.shopping.model.entity.Product;
import com.sale.shopping.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
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


    @Test
    @Transactional(rollbackFor = RuntimeException.class)
    @DisplayName("서비스 계층 상품 한개 선택 테스트 ")
    public void selectOneTest(){
        //given
        Integer id=1;
        Product product=Product.builder()
                .id(id)
                .productTitle("제목")
                .productImage("이미지주소")
                .productContent("내용")
                .productCount(1000)
                .productPrice(987654321)
                .build();
        Optional<Product> productOp=Optional.of(product);


        //stub : findById의 매개변수에 들어가는 값 의미 X
        when(productRepository.findById(id)).thenReturn(productOp);


        //when
        ProductResponseDTO productResponseDTO=productRepository.findById(id).get().toDTO();

        //then
        assertEquals(productResponseDTO.getProductTitle(),product.getProductTitle());
        assertEquals(productResponseDTO.getProductImage(),product.getProductImage());
        assertEquals(productResponseDTO.getProductContent(),product.getProductContent());
        assertEquals(productResponseDTO.getProductPrice(),product.getProductPrice());
        assertEquals(productResponseDTO.getProductCount(),product.getProductCount());
    }
    @Test
    @Transactional(rollbackFor = RuntimeException.class)
    @DisplayName("서비스 계층 상품 전체 선택 테스트 ")
    public void selectAllTest() {
        //given
        List<Product> products = new ArrayList<>();
        products.add(Product.builder().id(1).productTitle("제목1").productImage("이미지주소")
                .productContent("내용").productPrice(987654321).productCount(1000).build());

        products.add(Product.builder().id(1).productTitle("제목2").productImage("이미지주소")
                .productContent("내용").productPrice(987654321).productCount(1000).build());

        //stub
        when(productRepository.findAll()).thenReturn(products);

        //when
        List<ProductResponseDTO> productResponseDTOList=productRepository.findAll().stream()
                // .map((bookPS) -> bookPS.toDto())
                .map(Product::toDTO)
                .collect(Collectors.toList());

        //then
        assertEquals(productResponseDTOList.get(0).getProductTitle(),"제목1");
        assertEquals(productResponseDTOList.get(1).getProductTitle(),"제목2");


    }

    @Test
    @Transactional(rollbackFor = RuntimeException.class)
    @DisplayName("서비스 계층 상품 수정 테스트 ")
    public void modifyTest() {
        //given
        String productRequestDTOTitle="수정된제목";

        Integer id=1;

        //stub
        Product product=Product.builder()
                .productTitle("제목")
                .productImage("이미지위치")
                .productContent("내용")
                .productPrice(987654321)
                .productCount(1000)
                .build();

        Product productPS=productRepository.save(product);

        //when
        productPS=Product.builder().productTitle(productRequestDTOTitle).build();


        //then
        assertEquals(productPS.getProductTitle(),productRequestDTOTitle);





    }


}
