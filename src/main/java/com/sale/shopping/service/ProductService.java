package com.sale.shopping.service;

import com.sale.shopping.model.dto.ProductRequestDTO;
import com.sale.shopping.model.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO insertProduct(ProductRequestDTO prdouctRequestDTO);

    void deleteProduct(Integer id);

    ProductResponseDTO selectOneProduct(Integer id);

    List<ProductResponseDTO>  selectAllProduct();

    ProductResponseDTO modifyProduct(Integer id,ProductRequestDTO productRequestDTO);








}
