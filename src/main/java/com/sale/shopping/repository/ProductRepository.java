package com.sale.shopping.repository;

import com.sale.shopping.model.dto.ProductResponseDTO;
import com.sale.shopping.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository  extends JpaRepository<Product,Integer> {


    Optional<Product> findByProductTitle(String productTitle);

    void deleteByProductTitle(String productTitle);


    Page<Product> findAllByProductCategory(String productCategory, Pageable pageable);


    Optional<Product> findByProductCategoryAndProductTitle(String productCategory,String productTitle);



    List<?> findTop4ByProductCategoryOrderByIdDesc(String productCategory);
}
