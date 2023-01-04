package com.sale.shopping.model.dto;

import com.sale.shopping.model.entity.Product;
import com.sale.shopping.model.entity.ProductOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductOrderRequestDTO {

    private Integer productOrderCount;
    private Product product;


    //주문자
    private String productOrderName;

    //주소
    private String productOrderAddress;

    private String productOrderAddress1;

    //상세 주소
    private String productOrderAddress2;

    //연락처
    private String productOrderPhone;

    //주문 번호
    private String productOrderNumber;


    public ProductOrder toEntity(){
        return ProductOrder.builder()
                .productOrderCount(productOrderCount)
                .productOrderName(productOrderName)
                .productOrderAddress(productOrderAddress)
                .productOrderAddress1(productOrderAddress1)
                .productOrderAddress2(productOrderAddress2)
                .productOrderPhone(productOrderPhone)
                .productOrderNumber(productOrderNumber)
                .product(product)
                .build();
    }
}
