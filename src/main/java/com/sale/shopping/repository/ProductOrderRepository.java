package com.sale.shopping.repository;


import com.sale.shopping.model.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductOrderRepository extends JpaRepository<ProductOrder,Integer> {
    Optional<ProductOrder> findByProductOrderNumber(String productOrderNumber);

}