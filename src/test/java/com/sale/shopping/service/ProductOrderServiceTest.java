package com.sale.shopping.service;


import com.sale.shopping.model.dto.ProductOrderRequestDTO;
import com.sale.shopping.model.dto.ProductOrderResponseDTO;
import com.sale.shopping.model.dto.ProductRequestDTO;
import com.sale.shopping.model.dto.ProductResponseDTO;
import com.sale.shopping.model.entity.Product;
import com.sale.shopping.model.entity.ProductOrder;
import com.sale.shopping.repository.ProductOrderRepository;
import com.sale.shopping.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class ProductOrderServiceTest {



    @InjectMocks //인터페이스 보다는 객체를 리턴해야함  -->인터페이스 쓰려면 , 해당 인터페이스를 구현한 클래스
    //객체 생성해야 하는 과정 필요 !
    private ProductOrderServiceImpl productOrderService;

    @Mock
    private ProductOrderRepository productOrderRepository;


    @Test
    @Transactional(rollbackFor = RuntimeException.class)
    @DisplayName("서비스 계층 주문 등록 테스트 ")
    public void insertTest(){
        //given
        ProductRequestDTO productRequestDTO=ProductRequestDTO.builder()
                .productTitle("제목")
                .productImage("이미지위치")
                .productContent("내용")
                .productPrice(987654321)
                .productCount(1000)
                .build();

        ProductOrderRequestDTO productOrderRequestDTORequestDTO=ProductOrderRequestDTO.builder()
                .productOrderCount(50)
                .product(productRequestDTO.toEntity())
                .build();


        //stub
        when(productOrderRepository.save(any())).thenReturn(productOrderRequestDTORequestDTO.toEntity());

        //when
        ProductOrder productOrderPS=productOrderRepository.save(productOrderRequestDTORequestDTO.toEntity());

        //then
        assertEquals(productOrderPS.getProductOrderCount(),productOrderRequestDTORequestDTO.getProductOrderCount());
        assertEquals(productOrderPS.getProduct(),productOrderRequestDTORequestDTO.getProduct());

    }

    @Test
    @Transactional(rollbackFor = RuntimeException.class)
    @DisplayName("서비스 계층 주문 삭제 테스트 ")
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

        ProductOrderRequestDTO productOrderRequestDTORequestDTO=ProductOrderRequestDTO.builder()
                .productOrderCount(50)
                .product(productRequestDTO.toEntity())
                .build();

        //stub
        when(productOrderRepository.save(any())).thenReturn(productOrderRequestDTORequestDTO.toEntity());

        //when
        ProductOrder productOrderPS=productOrderRepository.save(productOrderRequestDTORequestDTO.toEntity());
        productOrderRepository.deleteById(1);

        //then
        assertFalse(productOrderRepository.findById(id).isPresent());
    }


    @Test
    @Transactional(rollbackFor = RuntimeException.class)
    @DisplayName("서비스 계층 주문 한개 선택 테스트 ")
    public void selectOneTest(){
        //given
        Integer id=1;
        ProductRequestDTO productRequestDTO=ProductRequestDTO.builder()
                .productTitle("제목")
                .productImage("이미지위치")
                .productContent("내용")
                .productPrice(987654321)
                .productCount(1000)
                .build();

        ProductOrder productOrder=ProductOrder.builder()
                .productOrderCount(50)
                .product(productRequestDTO.toEntity())
                .build();

        Optional<ProductOrder> productOrderOp=Optional.of(productOrder);


        //stub : findById의 매개변수에 들어가는 값 의미 X
        when(productOrderRepository.findById(id)).thenReturn(productOrderOp);


        //when
        ProductOrderResponseDTO productOrderResponseDTO=productOrderRepository.findById(id).get().toDTO();

        //then
        assertEquals(productOrderResponseDTO.getProductOrderCount(),productOrder.getProductOrderCount());
        assertEquals(productOrderResponseDTO.getProduct(),productOrder.getProduct());
    }
    @Test
    @Transactional(rollbackFor = RuntimeException.class)
    @DisplayName("서비스 계층 주문 전체 선택 테스트 ")
    public void selectAllTest() {
        //given

        ProductRequestDTO productRequestDTO=ProductRequestDTO.builder()
                .productTitle("제목")
                .productImage("이미지위치")
                .productContent("내용")
                .productPrice(987654321)
                .productCount(1000)
                .build();

        List<ProductOrder> productOrderList = new ArrayList<>();

        productOrderList.add(ProductOrder.builder()
                .productOrderCount(50)
                .product(productRequestDTO.toEntity())
                .build());

        productOrderList.add(ProductOrder.builder()
                .productOrderCount(200)
                .product(productRequestDTO.toEntity())
                .build());



        //stub
        when(productOrderRepository.findAll()).thenReturn(productOrderList);

        //when
        List<ProductOrderResponseDTO> productOrderResponseDTOList=productOrderRepository.findAll().stream()
                // .map((bookPS) -> bookPS.toDto())
                .map(ProductOrder::toDTO)
                .collect(Collectors.toList());

        //then
        assertEquals(productOrderResponseDTOList.get(0).getProductOrderCount(),50);
        assertEquals(productOrderResponseDTOList.get(1).getProductOrderCount(),200);


    }

    @Test
    @Transactional(rollbackFor = RuntimeException.class)
    @DisplayName("서비스 계층 주문 수정 테스트 ")
    public void modifyTest() {
        //given
        Integer newCount=1111;

        Integer id=1;

        ProductRequestDTO productRequestDTO=ProductRequestDTO.builder()
                .productTitle("제목")
                .productImage("이미지위치")
                .productContent("내용")
                .productPrice(987654321)
                .productCount(1000)
                .build();

        //stub
        ProductOrder productOrder=ProductOrder.builder()
                .productOrderCount(newCount)
                .product(productRequestDTO.toEntity())
                .build();


        ProductOrder productOrderPS=productOrderRepository.save(productOrder);

        //when
        productOrderPS=ProductOrder.builder().productOrderCount(newCount).build();


        //then
        assertEquals(productOrderPS.getProductOrderCount(),newCount);





    }


}
