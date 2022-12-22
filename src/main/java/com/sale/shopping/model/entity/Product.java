package com.sale.shopping.model.entity;


import com.sale.shopping.model.dto.ProductResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String productTitle;

    @Column(nullable = false)
    private String productImage;

    @Column(nullable = false)
    private Integer productPrice;

    @Column(nullable = false)
    private String productContent;

    @Column(nullable = false)
    private Integer productCount;



    public ProductResponseDTO toDTO(){
        return ProductResponseDTO.builder()
                .productTitle(productTitle)
                .productImage(productImage)
                .productPrice(productPrice)
                .productContent(productContent)
                .productCount(productCount)
                .build();
    }

}
