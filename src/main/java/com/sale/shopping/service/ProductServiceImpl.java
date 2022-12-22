package com.sale.shopping.service;

import com.sale.shopping.model.dto.ProductRequestDTO;
import com.sale.shopping.model.dto.ProductResponseDTO;
import com.sale.shopping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl  implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDTO insertProduct(ProductRequestDTO prdouctRequestDTO) {
        return null;
    }

    @Override
    public void deleteProduct(Integer id) {

    }

    @Override
    public ProductResponseDTO selectOneProduct(Integer id) {
        return null;
    }

    @Override
    public List<ProductResponseDTO> selectAllProduct() {
        return null;
    }

    @Override
    public ProductResponseDTO modifyProduct(ProductRequestDTO productRequestDTO) {
        return null;
    }
}
