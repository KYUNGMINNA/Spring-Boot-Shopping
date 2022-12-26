package com.sale.shopping.controller.api;

import com.sale.shopping.model.dto.CommonDTO;
import com.sale.shopping.model.dto.ProductRequestDTO;
import com.sale.shopping.model.dto.ProductResponseDTO;

import com.sale.shopping.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductAPIController {

    private final ProductServiceImpl productService;


    //select
    @GetMapping("/product")
    public ResponseEntity<Object> SelectAllProductAPI(){
        List<ProductResponseDTO> productResponseDTOList=productService.selectAllProduct();
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(productResponseDTOList).build(),HttpStatus.OK);
    }
    //select
    @GetMapping("/product/{id}")
    public ResponseEntity<Object> SelectOneProductAPI(@PathVariable Integer id){
        ProductResponseDTO productResponseDTO =productService.selectOneProduct(id);
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(productResponseDTO).build(),HttpStatus.OK);

    }

    //insert
    @PostMapping("/product")
    public ResponseEntity<Object> insertProductAPI(@RequestBody  ProductRequestDTO productRequestDTO){
        ProductResponseDTO productResponseDTO=productService.insertProduct(productRequestDTO);
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(productResponseDTO).build(),HttpStatus.CREATED);
    }

    //delete
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Object> deleteProductAPI(@PathVariable Integer id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(null).build(),HttpStatus.OK);
    }

    //update
    @PutMapping("/product/{id}")
    public ResponseEntity<Object> modifyProductAPI(@PathVariable Integer id,@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = productService.modifyProduct(id, productRequestDTO);
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(productResponseDTO).build(),HttpStatus.OK);

    }

    //update
    @PutMapping("/product/{id}/{count}")
    public ResponseEntity<Object> purchaseProduct(@PathVariable Integer id,@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = productService.modifyProduct(id, productRequestDTO);
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(productResponseDTO).build(),HttpStatus.OK);




}
