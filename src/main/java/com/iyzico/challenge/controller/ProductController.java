package com.iyzico.challenge.controller;


import com.iyzico.challenge.dto.ProductRequest;
import com.iyzico.challenge.dto.ProductResponse;
import com.iyzico.challenge.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/product")

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("add")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest, HttpServletRequest httpServletRequest){
        ProductResponse productResponse = productService.addProduct(productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @GetMapping("list")
    public ResponseEntity<List<ProductResponse>> listProducts(HttpServletRequest httpServletRequest){
        return new ResponseEntity<>(productService.listProducts(), HttpStatus.OK);
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<ProductResponse> removeProduct(@PathVariable Long id){
        return new ResponseEntity<>(productService.removeProduct(id), HttpStatus.NO_CONTENT);
    }

    @PutMapping("update")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest){
        return new ResponseEntity<>(productService.updateProduct(id, productRequest), HttpStatus.OK );
    }

}
