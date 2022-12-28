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
}
