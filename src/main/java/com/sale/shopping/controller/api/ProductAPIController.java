package com.sale.shopping.controller.api;

import com.sale.shopping.model.dto.CommonDTO;
import com.sale.shopping.model.dto.ProductRequestDTO;
import com.sale.shopping.model.dto.ProductResponseDTO;
import com.sale.shopping.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductAPIController {

    private final ProductServiceImpl productService;

    @PostMapping("/product")
    public ResponseEntity<Object> insertProduct(@RequestBody  ProductRequestDTO productRequestDTO){

        ProductResponseDTO productResponseDTO=productService.insertProduct(productRequestDTO);
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(productResponseDTO).build(),HttpStatus.CREATED);
    }


}
