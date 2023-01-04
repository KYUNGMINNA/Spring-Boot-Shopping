package com.sale.shopping.service;

import com.sale.shopping.model.dto.ProductOrderRequestDTO;
import com.sale.shopping.model.dto.ProductOrderResponseDTO;
import com.sale.shopping.model.dto.ProductRequestDTO;
import com.sale.shopping.model.dto.ProductResponseDTO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ProductOrderService {

    ProductOrderResponseDTO insertProduct(ProductOrderRequestDTO productOrderRequestDTO );

    void deleteProduct(Integer id);

    ProductOrderResponseDTO selectOneProduct(Integer id);

    ProductOrderResponseDTO selectProductOrderNumber(String productOrderNumber);

    List<ProductOrderResponseDTO>  selectAllProduct();

    ProductOrderResponseDTO modifyProduct(Integer id,ProductOrderRequestDTO productOrderRequestDTO);






}
