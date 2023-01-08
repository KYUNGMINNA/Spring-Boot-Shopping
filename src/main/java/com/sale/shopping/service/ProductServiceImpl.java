package com.sale.shopping.service;

import com.sale.shopping.model.dto.ProductRequestDTO;
import com.sale.shopping.model.dto.ProductResponseDTO;
import com.sale.shopping.model.entity.Product;
import com.sale.shopping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public ProductResponseDTO selectOneProduct(String productCategory,String productTitle) {
        return productRepository.findByProductCategoryAndProductTitle(productCategory,productTitle).get().toDTO();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Page<Product> selectAllProduct(String productCategory, Pageable pageable) {
        return productRepository.findAllByProductCategory(productCategory, pageable);
    }

    @Override
    public List<ProductResponseDTO> selectLimitProduct(String productCateogry) {
        return (List<ProductResponseDTO>) productRepository.findTop4ByProductCategoryOrderByIdDesc(productCateogry);
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
