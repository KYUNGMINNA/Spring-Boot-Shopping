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
public class ProductRequestDTO {


    @NotBlank

    private String productTitle;
    @NotBlank
    private String productImage;
    @NotBlank
    private String productContent;
    @NotBlank
    private Integer productPrice;
    @NotBlank
    private Integer productCount;

    public Product toEntity(){
        return Product.builder()
                .productTitle(productTitle)
                .productImage(productImage)
                .productContent(productContent)
                .productPrice(productPrice)
                .productCount(productCount)
                .build();
    }
}
