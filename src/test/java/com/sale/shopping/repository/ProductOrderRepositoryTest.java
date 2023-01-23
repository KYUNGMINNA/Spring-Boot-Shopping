package com.sale.shopping.repository;



import com.sale.shopping.model.entity.Product;
import com.sale.shopping.model.entity.ProductOrder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


//실제 DB로 테스트 하겠다.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Slf4j
public class ProductOrderRepositoryTest {



    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private ProductRepository productRepository;

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


            ProductOrder productOrder=ProductOrder.builder()
                    .productOrderCount(i*10)
                    .product(productRepository.save(product))
                    .build();

            productOrderRepository.save(productOrder);
        }




    }

    @Test
    @DisplayName("주문 등록 테스트")
    public void insertTest(){
        //given
        Product product = Product.builder()
                .productTitle("제목")
                .productImage("이미지주소")
                .productContent("내용")
                .productPrice(987654321)
                .productCount(1000)
                .build();


        ProductOrder productOrder=ProductOrder.builder()
                                    .productOrderCount(10)
                                    .product(product)
                                    .build();

        //when
        ProductOrder productOrderPS = productOrderRepository.save(productOrder);

        //then

        assertEquals(productOrderPS.getProduct(),productOrder.getProduct());

    }

    @Test
    @DisplayName("주문 조회 테스트")
    public void selectOneTest(){
        //given
        Integer id=1;

        //when
        ProductOrder productOrder= productOrderRepository.findById(1).get();

        //then
        assertEquals(productOrder.getId(),id);

    }

    @Test
    @DisplayName("주문 전체 조회 테스트")
    public void selectAllTest(){
        //given

        //when
        List<ProductOrder> productOrderList= productOrderRepository.findAll();

        //then
        assertEquals(productOrderList.size(),9);

    }

    @Test
    @DisplayName("주문 삭제 테스트")
    public void deleteTest(){
        //given

        Product product = Product.builder()
                .productTitle("제목")
                .productImage("이미지주소")
                .productContent("내용")
                .productPrice(987654321)
                .productCount(1000)
                .build();


        ProductOrder productOrder=ProductOrder.builder()
                .productOrderCount(10)
                .product(product)
                .build();


        productOrderRepository.save(productOrder);

        //when
        productOrderRepository.delete(productOrder);

    }

    @Test
    @DisplayName("주문 수정 테스트")
    public void modifyTest(){
        //given
        Integer id=7;

        ProductOrder productOrder=ProductOrder.builder()
                .id(id)
                .productOrderCount(111)
                .product(Product.builder()
                        .productTitle("제목7")
                        .productImage("이미지주소")
                        .productContent("내용")
                        .productPrice(987654321)
                        .productCount(1000)
                        .build())
                .build();
        ProductOrder productOrderPS=productOrderRepository.save(productOrder);

        assertEquals(productOrderPS.getProductOrderCount(),productOrder.getProductOrderCount());

    }



}
