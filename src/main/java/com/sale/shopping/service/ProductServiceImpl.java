package com.sale.shopping.service;

import com.sale.shopping.model.dto.ProductRequestDTO;
import com.sale.shopping.model.dto.ProductResponseDTO;
import com.sale.shopping.model.entity.Product;
import com.sale.shopping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl  implements ProductService{

    private final ProductRepository productRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public ProductResponseDTO insertProduct(ProductRequestDTO prdouctRequestDTO) {
       Product productPS=productRepository.save(prdouctRequestDTO.toEntity());
       return productPS.toDTO();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public ProductResponseDTO selectOneProduct(Integer id) {
        return productRepository.findById(id).get().toDTO();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public List<ProductResponseDTO> selectAllProduct() {
        return productRepository.findAll().stream()
                .map(Product::toDTO)
                .collect(Collectors.toList());
    }


    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public ProductResponseDTO modifyProduct(Integer id ,ProductRequestDTO productRequestDTO) {
        Optional<Product> productOP = productRepository.findById(id);
    //    if(productOP.isPresent()){
            Product productPS=productOP.get();
            productPS.update(productRequestDTO);
            return productPS.toDTO();
    /*    }
        else{
            throw  new RuntimeException("조회할 수 없습니다.");
        }*/


    }

}
