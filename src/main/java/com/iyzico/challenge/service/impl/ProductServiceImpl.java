package com.iyzico.challenge.service.impl;

import com.iyzico.challenge.dto.ProductRequest;
import com.iyzico.challenge.dto.ProductResponse;
import com.iyzico.challenge.repository.ProductRepository;
import com.iyzico.challenge.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        return null;
    }

    @Override
    public ProductResponse updateProduct(String productId, ProductRequest productRequest) {
        return null;
    }

    @Override
    public ProductResponse removeProduct(String productId) {
        return null;
    }

    @Override
    public List<ProductResponse> listProducts() {
        return null;
    }

}
