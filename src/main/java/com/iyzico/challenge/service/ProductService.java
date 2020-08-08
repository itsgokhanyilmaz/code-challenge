package com.iyzico.challenge.service;

import com.iyzico.challenge.dto.ProductRequest;
import com.iyzico.challenge.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse addProduct(ProductRequest productRequest);

    ProductResponse updateProduct(Long productId, ProductRequest productRequest);

    ProductResponse removeProduct(Long productId);

    List<ProductResponse> listProducts();
}
