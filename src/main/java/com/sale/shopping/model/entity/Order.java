package com.sale.shopping.model.entity;


import com.sale.shopping.model.dto.ProductRequestDTO;
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
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne  // 하나의 상품은 여러개의 주문 허용
    @JoinColumn(name = "userId")
    private Product productId;


    @Column(nullable = false)
    private Integer orderCount;




}
