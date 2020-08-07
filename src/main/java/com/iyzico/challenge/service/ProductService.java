package com.iyzico.challenge.service;

import com.iyzico.challenge.dto.ProductRequest;
import com.iyzico.challenge.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse addProduct(ProductRequest productRequest);

    ProductResponse updateProduct(String productId, ProductRequest productRequest);

    ProductResponse removeProduct(String productId);

    List<ProductResponse> listProducts();
}
