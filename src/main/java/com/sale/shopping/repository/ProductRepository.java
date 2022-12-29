package com.sale.shopping.repository;

import com.sale.shopping.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository  extends JpaRepository<Product,Integer> {


    Optional<Product> findByProductTitle(String productTitle);

    void deleteByProductTitle(String productTitle);


}
