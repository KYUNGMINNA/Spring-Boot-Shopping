package com.sale.shopping.model.entity;


import com.sale.shopping.model.dto.ProductOrderResponseDTO;
import com.sale.shopping.model.dto.ProductResponseDTO;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@ToString
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer productOrderCount;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    private String productOrderName;
    private String productOrderAddress;
    private String productOrderAddress1;

    private String productOrderAddress2;

    private String productOrderPhone;

    private String productOrderNumber;


    public ProductOrderResponseDTO toDTO() {
        return ProductOrderResponseDTO.builder()
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
