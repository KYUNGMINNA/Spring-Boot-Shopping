package com.sale.shopping.repository;


import com.mysql.cj.log.LogFactory;
import com.sale.shopping.model.entity.Product;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


//실제 DB로 테스트 하겠다.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class ProductRepositoryTest {

    private final Logger log = LoggerFactory.getLogger(ProductRepositoryTest.class);


    @Autowired
    private ProductRepository productRepository;

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

        log.info("------------------------------------------------------------------------------------------");
        log.info("사이즈!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+productRepository.findAll().size());
        log.info("------------------------------------------------------------------------------------------");


    }




    //Auto_increment 초기화
    //@Sql("classpath:db/tableInit.sql")
    @Test
    @DisplayName("물건 등록 테스트")
    public void insertTest(){
        //given
        Product product=Product.builder()
                .productTitle("제목입니다")
                .productImage("이미지주소입니다")
                .productContent("내용입니다")
                .productPrice(987654321)
                .productCount(1000)
                .build();

        //when
        Product productPS=productRepository.save(product);

        //then
        assertEquals(productPS.getProductTitle(),product.getProductTitle());
        assertEquals(productPS.getProductImage(),product.getProductImage());
        assertEquals(productPS.getProductCount(),product.getProductCount());
        assertEquals(productPS.getProductContent(),product.getProductContent());
        assertEquals(productPS.getProductPrice(),product.getProductPrice());
    }
    //@Sql("classpath:db/tableInit.sql")
    @Test
    @DisplayName("물건 삭제 테스트")
    public void deleteTest(){
        //given
        String productTitle="제목9";


        /*
        *   Product테이블의 기본키가 다른 테이블의 FK로 존재하고 있기 때문에, 기본키로 삭제하면 오류가 발생함
        *   -->그래서 Repository에 새로운 delete메소드를 작성하여 대신 역할을 하게 했음
        * */

        //whe

        productRepository.deleteByProductTitle(productTitle);

        //then
        assertFalse(productRepository.findByProductTitle(productTitle).isPresent());

    }

    //@Sql("classpath:db/tableInit.sql")
    @Test
    @DisplayName("물건 한건 조회 테스트")
    public void selectOneTest(){
        //given
        Integer id=1;
        String productTitle="제목1";
        String productImage="이미지주소";
        String productContent="내용";
        Integer productPrice=987654321;
        Integer productCount=1000;


        //when
        Product productEntity=productRepository.findById(id).get();

        //then
        assertEquals(productEntity.getProductTitle(),productTitle);
        assertEquals(productEntity.getProductCount(),productCount);
        assertEquals(productEntity.getProductPrice(),productPrice);
        assertEquals(productEntity.getProductContent(),productContent);
        assertEquals(productEntity.getProductImage(),productImage);

    }

    //@Sql("classpath:db/tableInit.sql")
    @Test
    @DisplayName("물건 전체 조회 테스트")
    public void selectAllTest(){

        String productTitle1="제목1";
        String productTitle2="제목2";
        String productTitle3="제목3";

        //when
        List<Product> productList=productRepository.findAll();

        //then
        assertEquals(productList.get(0).getProductTitle(),"제목1");
        assertEquals(productList.get(1).getProductTitle(),"제목2");
        assertEquals(productList.get(2).getProductTitle(),"제목3");
    }


    //@Sql("classpath:db/tableInit.sql")
    @Test
    @DisplayName("물건 수정 테스트")
    public void modifyTest(){
        //given  -dirty checking
        Integer id=5;

        String productTitle="새로운제목";

        Product product=Product.builder()
                .id(id)
                .productTitle(productTitle)
                .productImage("이미지주소")
                .productContent("내용")
                .productPrice(987654321)
                .productCount(1000)
                .build();


        //when
        Product productEntity=productRepository.save(product);


        //then
        assertEquals(productEntity.getProductTitle(),productTitle);

    }



}
