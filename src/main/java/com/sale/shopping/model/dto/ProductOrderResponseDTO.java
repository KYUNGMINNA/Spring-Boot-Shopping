package com.sale.shopping.model.dto;

import com.sale.shopping.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductOrderResponseDTO {

    private Integer id;

    private Integer productOrderCount;
    private Product product;

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
}
