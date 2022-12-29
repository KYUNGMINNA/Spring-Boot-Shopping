package com.sale.shopping.model.entity;


import com.sale.shopping.model.dto.ProductRequestDTO;
import com.sale.shopping.model.dto.ProductResponseDTO;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@ToString
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
                .id(id)
                .productTitle(productTitle)
                .productImage(productImage)
                .productPrice(productPrice)
                .productContent(productContent)
                .productCount(productCount)
                .build();
    }

    public void update(ProductRequestDTO productRequestDTO){
        this.productTitle = productRequestDTO.getProductTitle();
        this.productImage = productRequestDTO.getProductImage();
        this.productPrice = productRequestDTO.getProductPrice();
        this.productContent = productRequestDTO.getProductContent();
        this.productCount = productRequestDTO.getProductCount();                         }
}
