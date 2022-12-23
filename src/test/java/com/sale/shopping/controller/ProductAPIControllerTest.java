package com.sale.shopping.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sale.shopping.controller.api.ProductAPIController;
import com.sale.shopping.model.dto.ProductRequestDTO;
import com.sale.shopping.model.dto.ProductResponseDTO;
import com.sale.shopping.service.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductAPIController.class)
public class ProductAPIControllerTest {

    private final Logger log = LoggerFactory.getLogger(ProductAPIControllerTest.class);

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private ProductServiceImpl productService;





    @Test
    @DisplayName("컨트롤러 상품 등록 테스트 ")
    public void insertTest() throws Exception {


        given(productService.insertProduct(ProductRequestDTO.builder()
                    .productTitle("제목")
                    .productImage("이미지주소")
                    .productContent("내용")
                    .productCount(1000)
                    .productPrice(987654321)
                    .build()))
                .willReturn(ProductResponseDTO.builder()
                        .id(1)
                        .productTitle("제목")
                        .productImage("이미지주소")
                        .productContent("내용")
                        .productCount(1000)
                        .productPrice(987654321)
                        .build());

        ProductRequestDTO productRequestDTO=ProductRequestDTO.builder()
                .productTitle("제목")
                .productImage("이미지주소")
                .productContent("내용")
                .productCount(1000)
                .productPrice(987654321)
                .build();

        String body = new ObjectMapper().writeValueAsString(productRequestDTO);

        mockMvc.perform(
                        post("/api/product")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.productTitle").exists())
                .andExpect(jsonPath("$.data.productImage").exists())
                .andExpect(jsonPath("$.data.productContent").exists())
                .andExpect(jsonPath("$.data.productCount").exists())
                .andExpect(jsonPath("$.data.productPrice").exists())
                .andDo(print());

        verify(productService).insertProduct(ProductRequestDTO.builder()
                .productTitle("제목")
                .productImage("이미지주소")
                .productContent("내용")
                .productCount(1000)
                .productPrice(987654321)
                .build());

    }
}
