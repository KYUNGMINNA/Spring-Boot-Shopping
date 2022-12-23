package com.sale.shopping.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.sale.shopping.controller.api.ProductAPIController;
import com.sale.shopping.model.dto.ProductRequestDTO;
import com.sale.shopping.model.dto.ProductResponseDTO;
import com.sale.shopping.model.entity.Product;
import com.sale.shopping.repository.ProductRepository;
import com.sale.shopping.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductAPIControllerTest {

    private final Logger log = LoggerFactory.getLogger(ProductAPIControllerTest.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productService;

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
        for(int i=1;i<10;i++){
            Product product=Product.builder()
                    .productTitle("제목"+i)
                    .productImage("이미지주소")
                    .productContent("내용")
                    .productPrice(987654321)
                    .productCount(1000)
                    .build();
            productRepository.save(product);
        }
    }


    @Test
    @DisplayName("컨트롤러 상품 등록 테스트 ")
    public void insertTest() throws Exception {
        // given
        ProductRequestDTO bookSaveReqDto = ProductRequestDTO.builder()
                .productTitle("제목")
                .productImage("이미지주소")
                .productContent("내용")
                .productPrice(987654321)
                .productCount(1000)
                .build();;


        String body = objectMapper.writeValueAsString(bookSaveReqDto);

        // when
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = testRestTemplate.exchange("/api/product", HttpMethod.POST, request, String.class);


        // then
        DocumentContext dc = JsonPath.parse(response.getBody());

        Integer statusCode = dc.read("$.statusCode");
        String productTitle = dc.read("$.data.productTitle");


        assertEquals(statusCode, HttpStatus.OK.value());
        assertEquals(productTitle, "제목");

    }

    @Test
    @DisplayName("컨트롤러 상품 전체 조회 테스트 ")
    public void selectAllTest() throws Exception {

        // given

        // when
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange("/api/product", HttpMethod.GET, request, String.class);

        // then
        DocumentContext dc = JsonPath.parse(response.getBody());


        Integer statusCode = dc.read("$.statusCode");

        String productTitle0 = dc.read("$.data[0].productTitle");
        String productTitle1 = dc.read("$.data[1].productTitle");
        String productTitle2 = dc.read("$.data[2].productTitle");
        String productTitle3 = dc.read("$.data[3].productTitle");
        String productTitle4 = dc.read("$.data[4].productTitle");


        assertEquals(statusCode, HttpStatus.OK.value());
        assertEquals(productTitle0, "제목1");
        assertEquals(productTitle1, "제목2");
        assertEquals(productTitle2, "제목3");
        assertEquals(productTitle3, "제목4");
        assertEquals(productTitle4, "제목5");
    }


    @Test
    @DisplayName("컨트롤러 상품 한 개 조회 테스트 ")
    public void selectOneTest() throws Exception {

        // given
        Integer id=1;


        // when
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange("/api/product/"+id, HttpMethod.GET, request, String.class);

        // then
        DocumentContext dc = JsonPath.parse(response.getBody());

        Integer statusCode = dc.read("$.statusCode");
        String productTitle = dc.read("$.data.productTitle");


        assertEquals(statusCode, HttpStatus.OK.value());
        assertEquals(productTitle, "제목1");

    }

    @Test
    @DisplayName("컨트롤러 상품 삭제 테스트 ")
    public void deleteTest() throws Exception {

        // given
        Integer id=1;


        // when
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange("/api/product/"+id, HttpMethod.DELETE, request, String.class);

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
        ProductRequestDTO productRequestDTO=ProductRequestDTO.builder()
                .productTitle("수정된제목")
                .productImage("수정된주소")
                .productContent("수정된내용")
                .productPrice(123456789)
                .productCount(9999)
                .build();;

        String body = objectMapper.writeValueAsString(productRequestDTO);

        // when
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = testRestTemplate.exchange("/api/product/"+id, HttpMethod.PUT, request, String.class);

        // then
        DocumentContext dc = JsonPath.parse(response.getBody());

        Integer statusCode = dc.read("$.statusCode");
        String productTitle = dc.read("$.data.productTitle");

        assertEquals(statusCode, HttpStatus.OK.value());
        assertEquals(productTitle, "수정된제목");
    }
}
