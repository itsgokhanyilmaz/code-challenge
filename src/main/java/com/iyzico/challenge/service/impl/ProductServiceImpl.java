package com.iyzico.challenge.service.impl;

import com.iyzico.challenge.dto.ProductRequest;
import com.iyzico.challenge.dto.ProductResponse;
import com.iyzico.challenge.entity.Product;
import com.iyzico.challenge.exception.ProductNotFoundException;
import com.iyzico.challenge.mapper.ProductMapper;
import com.iyzico.challenge.repository.ProductRepository;
import com.iyzico.challenge.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = productMapper.map.productRequestToProduct(productRequest);
        productRepository.save(product);
        return productMapper.map.productToProductResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Long productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException());
        product.setProductName(productRequest.getProductName());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setStockCount(productRequest.getStockCount());
        return productMapper.map.productToProductResponse(product);
    }

    @Override
    public ProductResponse removeProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException());
        productRepository.delete(product);
        return ProductMapper.map.productToProductResponse(product);
    }

    @Override
    public List<ProductResponse> listProducts() {
        return productRepository.findAll().stream()
                .map(product -> ProductMapper.map.productToProductResponse(product)).collect(Collectors.toList());
    }

}
