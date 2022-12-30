package com.sale.shopping.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.sale.shopping.model.dto.ProductOrderRequestDTO;
import com.sale.shopping.model.dto.ProductRequestDTO;
import com.sale.shopping.model.entity.Product;
import com.sale.shopping.model.entity.ProductOrder;
import com.sale.shopping.repository.ProductOrderRepository;
import com.sale.shopping.repository.ProductRepository;
import com.sale.shopping.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductOrderAPIControllerTest {

    private final Logger log = LoggerFactory.getLogger(ProductOrderAPIControllerTest.class);

    @Autowired
    private ProductOrderRepository productOrderRepository;
    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private TestRestTemplate testRestTemplate;
    private static ObjectMapper objectMapper;
    private static HttpHeaders headers;

    @BeforeAll
    public static void init() {
        objectMapper = new ObjectMapper();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @BeforeEach
    public void 데이터준비() {
        for(int i=1;i<10;i++) {
            Product product = Product.builder()
                    .productTitle("제목" + i)
                    .productImage("이미지주소")
                    .productContent("내용")
                    .productPrice(987654321)
                    .productCount(1000)
                    .build();
            productRepository.save(product);

            ProductOrder productOrder=ProductOrder.builder()
                    .orderCount(i*10)
                    .product(productRepository.findById(i/2==0?i:i-1).get())
                    .build();

            productOrderRepository.save(productOrder);
        }
    }


    @Test
    @DisplayName("컨트롤러 주문 등록 테스트 ")
    public void insertTest() throws Exception {
        // given
        ProductOrderRequestDTO productOrderRequestDTORequestDTO=ProductOrderRequestDTO.builder()
                .productOrderCount(50)
                .product(productRepository.findById(1).get())
                .build();


        String body = objectMapper.writeValueAsString(productOrderRequestDTORequestDTO);

        // when
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = testRestTemplate.exchange("/api/productorder", HttpMethod.POST, request, String.class);


        // then
        DocumentContext dc = JsonPath.parse(response.getBody());

        Integer statusCode = dc.read("$.statusCode");
        Integer productOrderCount = dc.read("$.data.productOrderCount");


        assertEquals(statusCode, HttpStatus.OK.value());
        assertEquals(productOrderCount, 50);

    }

    @Test
    @DisplayName("컨트롤러 상품 전체 조회 테스트 ")
    public void selectAllTest() throws Exception {

        // given

        // when
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange("/api/productorder", HttpMethod.GET, request, String.class);

        // then
        DocumentContext dc = JsonPath.parse(response.getBody());


        Integer statusCode = dc.read("$.statusCode");

        Integer productOrderCount1 = dc.read("$.data[0].productOrderCount");
        Integer productOrderCount2 = dc.read("$.data[1].productOrderCount");
        Integer productOrderCount3 = dc.read("$.data[2].productOrderCount");
        Integer productOrderCount4 = dc.read("$.data[3].productOrderCount");
        Integer productOrderCount5 = dc.read("$.data[4].productOrderCount");


        assertEquals(statusCode, HttpStatus.OK.value());
        assertEquals(productOrderCount1, 10);
        assertEquals(productOrderCount2, 20);
        assertEquals(productOrderCount3, 30);
        assertEquals(productOrderCount4, 40);
        assertEquals(productOrderCount5, 50);
    }


    @Test
    @DisplayName("컨트롤러 상품 한 개 조회 테스트 ")
    public void selectOneTest() throws Exception {

        // given
        Integer id=1;


        // when
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange("/api/productorder/"+id, HttpMethod.GET, request, String.class);

        // then
        DocumentContext dc = JsonPath.parse(response.getBody());

        Integer statusCode = dc.read("$.statusCode");
        Integer productOrderCount = dc.read("$.data.productOrderCount");


        assertEquals(statusCode, HttpStatus.OK.value());
        assertEquals(productOrderCount, 10);

    }

    @Test
    @DisplayName("컨트롤러 상품 삭제 테스트 ")
    public void deleteTest() throws Exception {

        // given
        Integer id=1;


        // when
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange("/api/productorder/"+id, HttpMethod.DELETE, request, String.class);

        // then
        DocumentContext dc = JsonPath.parse(response.getBody());

        Integer statusCode = dc.read("$.statusCode");

        assertEquals(statusCode, HttpStatus.OK.value());
    }

    @Test
    @DisplayName("컨트롤러 상품 수정 테스트 ")
    public void modifyTest() throws Exception {

        // given
        Integer id=1;

        ProductOrderRequestDTO productOrderRequestDTORequestDTO=ProductOrderRequestDTO.builder()
                .productOrderCount(500)
                .product(productRepository.findById(id).get())
                .build();

        String body = objectMapper.writeValueAsString(productOrderRequestDTORequestDTO);

        // when
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = testRestTemplate.exchange("/api/productorder/"+id, HttpMethod.PUT, request, String.class);

        // then
        DocumentContext dc = JsonPath.parse(response.getBody());

        Integer statusCode = dc.read("$.statusCode");
        Integer productOrderCount = dc.read("$.data.productOrderCount");

        assertEquals(statusCode, HttpStatus.OK.value());
        assertEquals(productOrderCount, 500);
    }
}
