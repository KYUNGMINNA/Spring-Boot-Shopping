package com.sale.shopping.controller.api;

import com.sale.shopping.model.dto.CommonDTO;
import com.sale.shopping.model.dto.ProductRequestDTO;
import com.sale.shopping.model.dto.ProductResponseDTO;
import com.sale.shopping.model.entity.Product;
import com.sale.shopping.repository.ProductRepository;
import com.sale.shopping.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/product")
    public ResponseEntity<Object> SelectAllProduct(){
        List<ProductResponseDTO> productResponseDTOList=productService.selectAllProduct();
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(productResponseDTOList).build(),HttpStatus.OK);

    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Object> SelectOneProduct(@PathVariable Integer id){
        ProductResponseDTO productResponseDTO =productService.selectOneProduct(id);
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(productResponseDTO).build(),HttpStatus.OK);

    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(null).build(),HttpStatus.OK);

    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Object> modifyProduct(@PathVariable Integer id,@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = productService.modifyProduct(id, productRequestDTO);
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(productResponseDTO).build(),HttpStatus.OK);

    }

}
