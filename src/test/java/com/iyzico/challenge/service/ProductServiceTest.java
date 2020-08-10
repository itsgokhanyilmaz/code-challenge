package com.iyzico.challenge.service;


import com.iyzico.challenge.dto.ProductRequest;
import com.iyzico.challenge.dto.ProductResponse;
import com.iyzico.challenge.entity.Product;
import com.iyzico.challenge.exception.ProductNotFoundException;
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

    private Product product;

    @Captor
    ArgumentCaptor<Product> captor;

    @Before
    public void init(){
        productRequest = new ProductRequest();
        productRequest.setProductName("iPhone");
        productRequest.setDescription("Cell Phone");
        productRequest.setPrice(BigDecimal.valueOf(12000));
        productRequest.setStockCount(20);

        product = new Product();
        product.setId(Long.valueOf(1223334444));
        product.setProductName("iPhone");
        product.setStockCount(20);
        product.setDescription("Cell Phone");
        product.setPrice(BigDecimal.valueOf(12000));

    }

    @Test
    public void addProduct_withAllProperties_success(){
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);

        ProductResponse productResponse = productService.addProduct(productRequest);

        assertEquals("iPhone", productResponse.getProductName());
        assertEquals("Cell Phone", productResponse.getDescription());
        assertEquals(BigDecimal.valueOf(12000), productResponse.getPrice());
        assertEquals( 20, (int)productResponse.getStockCount());
    }

    @Test
    public void updateProduct_withAllProperties_success(){
        Long productId = Long.valueOf(1223334444);
        Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.of(product));

        productService.updateProduct(Mockito.any(), productRequest);

        Mockito.verify(productRepository).save(captor.capture());

        assertEquals(productId, captor.getValue().getId());
        assertEquals(productRequest.getProductName(), captor.getValue().getProductName());
        assertEquals(productRequest.getDescription(), captor.getValue().getDescription());
        assertEquals(productRequest.getPrice(), captor.getValue().getPrice());
        assertEquals(productRequest.getStockCount(), captor.getValue().getStockCount());

    }

    @Test(expected = ProductNotFoundException.class)
    public void updateProduct_NoValidProduct_ProductNotFoundException(){
        Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        productService.updateProduct(Mockito.any(), productRequest);
    }

    @Test
    public void removeProduct_withValidParameter_success(){
        Long productId = Long.valueOf(1223334444);
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        productService.removeProduct(productId);

        InOrder inOrder = Mockito.inOrder(productRepository);
        inOrder.verify(productRepository).delete(product);
    }

    @Test(expected = ProductNotFoundException.class)
    public void removeProduct_NoValidParameter_ProductNotFoundException(){
        Long productId = Long.valueOf(1223334444);
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.empty());
        productService.removeProduct(productId);

    }

}
