package com.sale.shopping.model.dto;

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

    private Integer id;

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
}
