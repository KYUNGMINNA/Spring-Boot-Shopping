package com.sale.shopping.controller.api;

import com.sale.shopping.model.dto.CommonDTO;
import com.sale.shopping.model.dto.ProductOrderRequestDTO;
import com.sale.shopping.model.dto.ProductOrderResponseDTO;
import com.sale.shopping.repository.ProductRepository;
import com.sale.shopping.service.ProductOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductOrderAPIController {

    private final ProductOrderService productOrderService;

    private final ProductRepository productRepository;

    //select
    @GetMapping("/productorder")
    public ResponseEntity<Object> SelectAllProductAPI() {
        List<ProductOrderResponseDTO> productOrderResponseDTOList = productOrderService.selectAllProduct();
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(productOrderResponseDTOList).build(), HttpStatus.OK);
    }

    //select
    @GetMapping("/productorder/{id}")
    public ResponseEntity<Object> SelectOneProductAPI(@PathVariable Integer id) {
        ProductOrderResponseDTO productOrderResponseDTO = productOrderService.selectOneProduct(id);
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(productOrderResponseDTO).build(), HttpStatus.OK);
    }

    //select
    @GetMapping("/productorder/number/{productOrderNumber}")
    public ResponseEntity<Object> selectProductOrderNumber(@PathVariable String productOrderNumber) {
        ProductOrderResponseDTO productOrderResponseDTO = productOrderService.selectProductOrderNumber(productOrderNumber);
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(productOrderResponseDTO).build(), HttpStatus.OK);
    }

    //insert
    @PostMapping("/productorder")
    public ResponseEntity<Object> insertProductAPI(@RequestBody ProductOrderRequestDTO productOrderRequestDTO) {
        productOrderRequestDTO.setProduct(productRepository.findByProductTitle(productOrderRequestDTO.getProduct().getProductTitle()).get());
        ProductOrderResponseDTO productOrderResponseDTO = productOrderService.insertProduct(productOrderRequestDTO);
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(productOrderResponseDTO).build(), HttpStatus.CREATED);
    }

    //delete
    @DeleteMapping("/productorder/{id}")
    public ResponseEntity<Object> deleteProductAPI(@PathVariable Integer id) {
        productOrderService.deleteProduct(id);
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(null).build(), HttpStatus.OK);
    }

    //update
    @PutMapping("/productorder/{id}")
    public ResponseEntity<Object> modifyProductAPI(@PathVariable Integer id, @RequestBody ProductOrderRequestDTO productOrderRequestDTO) {
        ProductOrderResponseDTO productOrderResponseDTO = productOrderService.modifyProduct(id, productOrderRequestDTO);
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(productOrderResponseDTO).build(), HttpStatus.OK);

    }

}
