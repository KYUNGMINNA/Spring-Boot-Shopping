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

    //주문자
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



    public ProductOrderResponseDTO toDTO(){
        return ProductOrderResponseDTO.builder()
                .id(id)
                .productOrderCount(orderCount)
                .product(product)
                .build();
    }

}
