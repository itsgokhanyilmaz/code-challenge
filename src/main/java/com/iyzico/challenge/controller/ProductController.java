package com.iyzico.challenge.controller;


import com.iyzico.challenge.dto.MainResponse;
import com.iyzico.challenge.dto.ProductRequest;
import com.iyzico.challenge.dto.ProductResponse;
import com.iyzico.challenge.service.ProductService;
import com.iyzico.challenge.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("add")
    public ResponseEntity<MainResponse<ProductResponse>> addProduct(@RequestBody ProductRequest productRequest, HttpServletRequest httpServletRequest){
        ProductResponse productResponse = productService.addProduct(productRequest);
        return ResponseUtil.data(productResponse);
    }

    @GetMapping("list")
    public ResponseEntity<MainResponse<List<ProductResponse>>> listProducts(HttpServletRequest httpServletRequest){
        return ResponseUtil.data(productService.listProducts());
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<MainResponse<ProductResponse>> removeProduct(@PathVariable String id){
        return ResponseUtil.data(productService.removeProduct(id));
    }

    @PutMapping("update")
    public ResponseEntity<MainResponse<ProductResponse>> updateProduct(@PathVariable String id, @RequestBody ProductRequest productRequest){
        return ResponseUtil.data(productService.updateProduct(id, productRequest));
    }

}
