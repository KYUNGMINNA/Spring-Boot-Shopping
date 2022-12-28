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
    private Integer orderCount;

    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;


    public ProductOrderResponseDTO toDTO(){
        return ProductOrderResponseDTO.builder()
                .id(id)
                .productOrderCount(orderCount)
                .product(product)
                .build();
    }

}
