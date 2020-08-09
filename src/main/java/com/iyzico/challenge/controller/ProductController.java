package com.iyzico.challenge.controller;


import com.iyzico.challenge.dto.ProductRequest;
import com.iyzico.challenge.dto.ProductResponse;
import com.iyzico.challenge.service.ProductService;
import com.iyzico.challenge.util.ResponseUtil;
import com.iyzico.challenge.util.model.MainResponse;
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
    public ResponseEntity<MainResponse<ProductResponse>> addProduct(@Valid @RequestBody ProductRequest productRequest, HttpServletRequest httpServletRequest){
        ProductResponse productResponse = productService.addProduct(productRequest);
        return ResponseUtil.data(productResponse, HttpStatus.CREATED);
    }

    @GetMapping("list")
    public ResponseEntity<MainResponse<List<ProductResponse>>> listProducts(HttpServletRequest httpServletRequest){
        return ResponseUtil.data(productService.listProducts(), HttpStatus.OK);
    }

    @DeleteMapping("remove/{productId}")
    public ResponseEntity<MainResponse<ProductResponse>> removeProduct(@PathVariable Long productId){
        return ResponseUtil.data(productService.removeProduct(productId), HttpStatus.NO_CONTENT);
    }

    @PutMapping("update/{productId}")
    public ResponseEntity<MainResponse<ProductResponse>> updateProduct(@PathVariable Long productId, @RequestBody ProductRequest productRequest){
        return ResponseUtil.data(productService.updateProduct(productId, productRequest), HttpStatus.OK);
    }

}
