package com.iyzico.challenge.service;


import com.iyzico.challenge.dto.ProductRequest;
import com.iyzico.challenge.dto.ProductResponse;
import com.iyzico.challenge.repository.ProductRepository;
import com.iyzico.challenge.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@RestClientTest(ProductService.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Before
    public void init(){

    }

    @Test
    public void addProduct_withAllProperties_success(){
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("iPhone");
        productRequest.setDescription("Cell Phone");
        productRequest.setPrice(BigDecimal.valueOf(12000));
        productRequest.setStockCount(20);
        ProductResponse productResponse = productService.addProduct(productRequest);
        assertEquals("iPhone", productResponse.getProductName());
        assertEquals("Cell Phone", productResponse.getDescription());
        assertEquals(BigDecimal.valueOf(12000), productResponse.getPrice());
        assertEquals( 20, (int)productResponse.getStockCount());
    }


}
