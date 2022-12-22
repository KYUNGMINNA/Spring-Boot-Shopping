package com.sale.shopping.repository;

import com.sale.shopping.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository  extends JpaRepository<Product,Integer> {


}
