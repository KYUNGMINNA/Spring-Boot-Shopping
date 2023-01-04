package com.sale.shopping.service;

import com.sale.shopping.model.dto.ProductOrderRequestDTO;
import com.sale.shopping.model.dto.ProductOrderResponseDTO;
import com.sale.shopping.model.dto.ProductRequestDTO;
import com.sale.shopping.model.dto.ProductResponseDTO;
import com.sale.shopping.model.entity.Product;
import com.sale.shopping.model.entity.ProductOrder;
import com.sale.shopping.repository.ProductOrderRepository;
import com.sale.shopping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductOrderServiceImpl implements ProductOrderService{

    private final ProductOrderRepository productOrderRepository;
    private  final  ProductRepository productRepository;


    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public ProductOrderResponseDTO insertProduct(ProductOrderRequestDTO productOrderRequestDTO ){
        String uuid=UUID.randomUUID().toString();

        productOrderRequestDTO.setProductOrderNumber(uuid);
        productOrderRequestDTO.setProductOrderCount(productOrderRequestDTO.getProductOrderCount());

        ProductOrder productOrderPS=productOrderRepository.save(productOrderRequestDTO.toEntity());
        Product productPS= productRepository.findByProductTitle(productOrderRequestDTO.getProduct().getProductTitle()).get();
        if (productPS.getProductCount()==0 || productPS.getProductCount()<=0 ||productOrderRequestDTO.getProductOrderCount()>=10){
            throw  new RuntimeException();
        }

        productPS.purchase(productOrderRequestDTO.getProductOrderCount());

        return productOrderPS.toDTO();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void deleteProduct(Integer id) {
        productOrderRepository.deleteById(id);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public ProductOrderResponseDTO selectOneProduct(Integer id) {
        return productOrderRepository.findById(id).get().toDTO();
    }

    @Override
    public ProductOrderResponseDTO selectProductOrderNumber(String productOrderNumber) {
        return productOrderRepository.findByProductOrderNumber(productOrderNumber).get().toDTO();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public List<ProductOrderResponseDTO> selectAllProduct() {
        return productOrderRepository.findAll().stream()
                .map(ProductOrder::toDTO)
                .collect(Collectors.toList());
    }


    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public ProductOrderResponseDTO modifyProduct(Integer id ,ProductOrderRequestDTO productOrderRequestDTO) {
        Optional<ProductOrder> productOrderOP = productOrderRepository.findById(id);
    //    if(productOP.isPresent()){
            ProductOrder productOrderPS=productOrderOP.get();
            productOrderPS=productOrderRequestDTO.toEntity();
            return productOrderPS.toDTO();
    /*    }
        else{
            throw  new RuntimeException("조회할 수 없습니다.");
        }*/


    }



}
