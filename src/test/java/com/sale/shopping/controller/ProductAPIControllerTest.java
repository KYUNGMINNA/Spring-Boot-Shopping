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
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ProductAPIControllerTest {


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

   /* @BeforeEach
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
    }*/

    /*
insert into Product(productCategory, productContent, productCount, productImage,
                    productPrice, productTitle)
values
('샐러드','내용',100,'salad1',8300,'닭가슴살 샐러드'),
('샐러드','내용',100,'salad2',9000,'닭가슴살 비엔나 샐러드'),
('샐러드','내용',100,'salad3',9000,'크래미 샐러드'),
('샐러드','내용',100,'salad4',8100,'치즈 샐러드'),
('샐러드','내용',100,'salad5',8700,'닭가슴살 술붗갈비맛 샐러드'),
('샐러드','내용',100,'salad6',9600,'닭가슴살 옥수수톡 샐러드'),
('샐러드','내용',100,'salad7',9700,'닭가슴살 깻잎가득 샐러드'),
('샐러드','내용',100,'salad8',9300,'파스타 샐러드'),
('샐러드','내용',100,'salad9',9200,'훈제오리 샐러드'),
('샐러드','내용',100,'salad10',8900,'그린 샐러드'),
('샐러드','내용',100,'salad11',9000,'채소만 베이직 믹스 샐러드'),
('샐러드','내용',100,'salad12',9900,'채소만 베이직 플러스 믹스 샐러드'),
('샐러드','내용',100,'salad13',8700,'채소만 레드비트 믹스 샐러드'),
('샐러드','내용',100,'salad14',9200,'채소만 루꼴라 믹스 샐러드'),
('샐러드','내용',100,'salad15',9300,'채소만 비타민 믹스 샐러드'),
('샐러드','내용',100,'salad16',9400,'멕시칸 타코 샐러드'),
('샐러드','내용',100,'salad17',10700,'갈릭페퍼 로스트 닭다리살 샐러드'),
('샐러드','내용',100,'salad18',1100,'레드칠리 로스트 닭가슴살 샐러드'),
('샐러드','내용',100,'salad19',10300,'이탈리안 더블 햄 샐러드'),
('샐러드','내용',100,'salad20',10200,'페퍼콘 닭가슴살 샐러드');

insert Product(productCategory, productContent, productCount, productImage, productPrice, productTitle)
values
('도시락','내용',200, 'lunchbox1',6100,'다섯가지나물밥 불고기 오믈렛'),
('도시락','내용',200, 'lunchbox2',6200, '곤드레 나물밥'),
('도시락','내용',200, 'lunchbox3',6300, '고구마밥'),
('도시락','내용',200, 'lunchbox4',6400, '계란 새우볶음밥'),
('도시락','내용',200, 'lunchbox5',6200, '닭가슴살 볶음밥'),
('도시락','내용',200, 'lunchbox6',6000, '탄두리 닭가슴살 현미밥'),
('도시락','내용',200, 'lunchbox7',6000, '취나물밥 매콤 제육볶음'),
('도시락','내용',200, 'lunchbox8',5900, '근채 영양밥 닭가슴살'),
('도시락','내용',200, 'lunchbox9',5700, '시래기 보리밥 불고기'),
('도시락','내용',200, 'lunchbox10',5800, '나시고랭 닭가슴살 소시지'),
('도시락','내용',200, 'lunchbox11',5800, '빠에야 볶음밥'),
('도시락','내용',200, 'lunchbox12',5900, '야채 볶음밥'),
('도시락','내용',200, 'lunchbox13',6000, '김치 곤약 볶음밥'),
('도시락','내용',200, 'lunchbox14',6200, '계란 곤약 볶음밥 함박 스테이크'),
('도시락','내용',200, 'lunchbox15',6100, '계란 곤약 볶음밥 핫스테이크'),
('도시락','내용',200, 'lunchbox16',6200, '퀴노야 곤약밥'),
('도시락','내용',200, 'lunchbox17',6100, '곤드레 곤약밥'),
('도시락','내용',200, 'lunchbox18',5900, '귀리 곤약밥'),
('도시락','내용',200, 'lunchbox19',6000, '미니컵밥 백김치멸치'),
('도시락','내용',200, 'lunchbox20',5500, '미니컵밥 잡채 덮밥');




insert Product(productCategory, productContent, productCount,
               productImage, productPrice, productTitle)
values
('간편식','내용',500,'convenience1',4000,'닭가슴살 만두 오리지널'),
('간편식','내용',500,'convenience2',4000,'닭가슴살 만두 매콤김치'),
('간편식','내용',500,'convenience3',3600,'닭가슴살 슬라이스 갈비맛'),
('간편식','내용',500,'convenience4',3600,'닭가슴살 슬라이스 양념치킨맛'),
('간편식','내용',500,'convenience5',3600,'닭가슴살 슬라이스 커리맛'),
('간편식','내용',500,'convenience6',3600,'닭가슴살 소시지 오리지널'),
('간편식','내용',500,'convenience7',3600,'닭가슴살 소시지 구운마늘'),
('간편식','내용',500,'convenience8',3600,'닭가슴살 소시지 매콤치즈'),
('간편식','내용',500,'convenience9',3500,'그릴 닭가슴살 숫불갈비맛'),
('간편식','내용',500,'convenience10',3500,'그릴 닭가슴볼 옥수수톡'),
('간편식','내용',500,'convenience11',3500,'그릴 닭가슴살 깻잎가득'),
('간편식','내용',500,'convenience12',3500,'포캣 리얼 요거트'),
('간편식','내용',500,'convenience13',3500,'한입 밤 고구마'),
('간편식','내용',500,'convenience14',4000,'포켓 아이스 홍시');



     */




    @Test
    public void testset(){

            Product product=Product.builder()
                    .productTitle("닭가슴살 샐러드")
                    .productPrice(7777)
                    .productImage("salad1")
                    .productContent("내용")
                    .productCount(1000)
                    .productCategory("샐러드")
                    .build();
            productRepository.save(product);




/*        for(int i=1;i<=30;i++){
            Product product=Product.builder()
                    .productTitle("제목"+i)
                    .productImage("salad"+i)
                    .productContent("내용"+i)
                    .productPrice(7777)
                    .productCount(1000+i)
                    .productCategory("샐러드")
                    .build();
            productRepository.save(product);
        }
        for(int i=101;i<=130;i++){
            Product product=Product.builder()
                    .productTitle("제목"+i)
                    .productImage("lunchbox"+i)
                    .productContent("내용"+i)
                    .productPrice(3333)
                    .productCount(100+i)
                    .productCategory("도시락")
                    .build();
            productRepository.save(product);
        }
        for(int i=201;i<=230;i++){
            Product product=Product.builder()
                    .productTitle("제목"+i)
                    .productImage("convenience"+i)
                    .productContent("내용"+i)
                    .productPrice(99999)
                    .productCount(300+i)
                    .productCategory("간편식")
                    .build();
            productRepository.save(product);
        }*/

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
