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

    public ProductOrder toEntity(){
        return ProductOrder.builder()
                .orderCount(productOrderCount)
                .product(product)
                .build();
    }
}
