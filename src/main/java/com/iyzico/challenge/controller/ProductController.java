package com.iyzico.challenge.controller;


import com.iyzico.challenge.dto.ProductRequest;
import com.iyzico.challenge.dto.ProductResponse;
import com.iyzico.challenge.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("add")
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest productRequest, HttpServletRequest httpServletRequest){
        ProductResponse productResponse = productService.addProduct(productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @GetMapping("list")
    public ResponseEntity<List<ProductResponse>> listProducts(HttpServletRequest httpServletRequest){
        List<ProductResponse> productResponses = productService.listProducts();
        return new ResponseEntity<>(productResponses, HttpStatus.CREATED);

    }

    @DeleteMapping("remove/{productId}")
    public ResponseEntity<ProductResponse> removeProduct(@PathVariable Long productId){
        ProductResponse productResponse = productService.removeProduct(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PutMapping("update/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productService.updateProduct(productId, productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

}
