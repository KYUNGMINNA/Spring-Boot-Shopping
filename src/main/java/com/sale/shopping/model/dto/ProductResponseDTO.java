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
public class ProductResponseDTO {


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

    private String productCategory;



     public static ProductResponseDTO fromEntity(Product product){
         return ProductResponseDTO.builder()
                 .productTitle(product.getProductTitle())
                 .productImage(product.getProductImage())
                 .productPrice(product.getProductPrice())
                 .productContent(product.getProductContent())
                 .productCount(product.getProductCount())
                 .productCategory(product.getProductCategory())
                 .build();

     }
}
