package com.iyzico.challenge.service;


import com.iyzico.challenge.dto.ProductRequest;
import com.iyzico.challenge.dto.ProductResponse;
import com.iyzico.challenge.entity.Product;
import com.iyzico.challenge.repository.ProductRepository;
import com.iyzico.challenge.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@RestClientTest(ProductService.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private ProductRequest productRequest;

    private Product savedProduct;

    @Captor
    ArgumentCaptor<Product> captor;

    @Before
    public void init(){
        productRequest = new ProductRequest();
        productRequest.setProductName("iPhone");
        productRequest.setDescription("Cell Phone");
        productRequest.setPrice(BigDecimal.valueOf(12000));
        productRequest.setStockCount(20);

        savedProduct = new Product();
        savedProduct.setId(new Long(1223334444));
        savedProduct.setProductName("iPhone");
        savedProduct.setStockCount(20);
        savedProduct.setDescription("Cell Phone");
        savedProduct.setPrice(BigDecimal.valueOf(12000));

    }

    @Test
    public void addProduct_withAllProperties_success(){
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(savedProduct);

        ProductResponse productResponse = productService.addProduct(productRequest);

        assertEquals("iPhone", productResponse.getProductName());
        assertEquals("Cell Phone", productResponse.getDescription());
        assertEquals(BigDecimal.valueOf(12000), productResponse.getPrice());
        assertEquals( 20, (int)productResponse.getStockCount());
    }

    @Test
    public void updateProduct_withAllProperties_success(){
        Long productId = new Long(1223334444);
        Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.of(savedProduct));

        Mockito.when(productRepository.save(Mockito.any())).thenReturn(savedProduct);
        ProductResponse productResponse = productService.updateProduct(productId, productRequest);

        Mockito.verify(productRepository).save(captor.capture());

        assertEquals(productResponse.getProductName(), captor.getValue().getProductName());
        assertEquals(productResponse.getDescription(), captor.getValue().getDescription());
        assertEquals(productResponse.getPrice(), captor.getValue().getPrice());
        assertEquals(productResponse.getStockCount(), captor.getValue().getStockCount());

    }

}
