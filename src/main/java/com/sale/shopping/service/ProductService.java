package com.sale.shopping.service;

import com.sale.shopping.model.dto.ProductRequestDTO;
import com.sale.shopping.model.dto.ProductResponseDTO;
import com.sale.shopping.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductResponseDTO insertProduct(ProductRequestDTO prdouctRequestDTO);

    void deleteProduct(Integer id);

    ProductResponseDTO selectOneProduct(String productCategory,String productTitle);

    Page<Product> selectAllProduct(String productCategory, Pageable pageable);

    List<ProductResponseDTO> selectLimitProduct(String productCateogry);

    ProductResponseDTO modifyProduct(Integer id,ProductRequestDTO productRequestDTO);








}
