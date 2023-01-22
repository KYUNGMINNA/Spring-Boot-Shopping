package com.sale.shopping.controller.api;

import com.sale.shopping.model.dto.CommonDTO;
import com.sale.shopping.model.dto.ProductRequestDTO;
import com.sale.shopping.model.dto.ProductResponseDTO;

import com.sale.shopping.model.entity.Product;
import com.sale.shopping.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductAPIController {
    private final ProductService productService;

    //select
    @GetMapping("/product")
    public ResponseEntity<Object> SelectAllProductAPI(String productCategory, Pageable pageable) {
        Page<Product> productResponseDTOList = productService.selectAllProduct(productCategory, pageable);
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(productResponseDTOList).build(), HttpStatus.OK);
    }

    @GetMapping("/product/productImage={productImage}")
    public ResponseEntity<byte[]> getProductImg(@PathVariable String productImage) {

        String folderName = "";
        if (productImage.charAt(0) == 'c') {
            folderName = "convenience\\";
        } else if (productImage.charAt(0) == 'l') {
            folderName = "lunchbox\\";
        } else if (productImage.charAt(0) == 's') {
            folderName = "salad\\";
        }
        ResponseEntity<byte[]> result = null;

        try {
            File file = new File("C:\\shopping\\image\\" + folderName + productImage + ".jpg");


            HttpHeaders headers = new HttpHeaders();

            headers.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/product/all")
    public ResponseEntity<Object> selectLimitProduct(String productCategory) {
        List<ProductResponseDTO> productResponseDTOList = productService.selectLimitProduct(productCategory);
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(productResponseDTOList).build(), HttpStatus.OK);
    }

    //select
    @GetMapping("/product/one")
    public ResponseEntity<Object> SelectOneProductAPI(@PathVariable String productCategory, String productTitle) {
        ProductResponseDTO productResponseDTO = productService.selectOneProduct(productCategory, productTitle);
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(productResponseDTO).build(), HttpStatus.OK);
    }


    //insert
    @PostMapping("/product")
    public ResponseEntity<Object> insertProductAPI(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = productService.insertProduct(productRequestDTO);
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(productResponseDTO).build(), HttpStatus.CREATED);
    }

    //delete
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Object> deleteProductAPI(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(null).build(), HttpStatus.OK);
    }

    //update
    @PutMapping("/product/{id}")
    public ResponseEntity<Object> modifyProductAPI(@PathVariable Integer id, @RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = productService.modifyProduct(id, productRequestDTO);
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.OK.value()).data(productResponseDTO).build(), HttpStatus.OK);
    }


}
