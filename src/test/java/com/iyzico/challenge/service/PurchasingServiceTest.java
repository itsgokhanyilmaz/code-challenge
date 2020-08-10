package com.iyzico.challenge.service;

import com.iyzico.challenge.dto.PurchasingRequest;
import com.iyzico.challenge.dto.PurchasingResponse;
import com.iyzico.challenge.entity.Product;
import com.iyzico.challenge.entity.Purchasing;
import com.iyzico.challenge.exception.ProductNotFoundException;
import com.iyzico.challenge.mapper.PurchasingMapper;
import com.iyzico.challenge.repository.ProductRepository;
import com.iyzico.challenge.repository.PurchasingRepository;
import com.iyzico.challenge.service.impl.PurchasingServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@RestClientTest(PurchasingService.class)
public class PurchasingServiceTest {

    @Mock
    private PurchasingRepository purchasingRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private IyzicoPaymentService iyzicoPaymentService;

    @InjectMocks
    private PurchasingServiceImpl purchasingService;

    private PurchasingRequest purchasingRequest;

    @Mock
    private PurchasingMapper purchasingMapper;

    private Purchasing purchasing;

    private Product product;

    @Before
    public void init(){
        purchasingRequest = new PurchasingRequest();
        purchasingRequest.setProductId(1223334444L);
        purchasingRequest.setProductCount(2);

        purchasing = new Purchasing();
        purchasing.setId(125L);
        purchasing.setProductId(1223334444L);
        purchasing.setProductCount(2);

        product = new Product();
        product.setId(1223334444L);
        product.setProductName("iPhone");
        product.setStockCount(20);
        product.setPrice(BigDecimal.valueOf(12000));
        product.setDescription("Cell Phone");

    }

    @Test
    @Transactional
    @Rollback
    public void purchaseProduct_withAllParameters_Success(){
        Long productId = new Long(1223334444L);
        BigDecimal total = new BigDecimal(24000);

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        iyzicoPaymentService.pay(total);
        Mockito.when(purchasingRepository.save(Mockito.any())).thenReturn(purchasing);

        PurchasingResponse purchasingResponse = purchasingService.purchaseProduct(purchasingRequest);

        assertEquals(new Long(1223334444L), purchasingResponse.getProductId());
        assertEquals(purchasingRequest.getProductCount(), purchasingResponse.getProductCount());

    }

    @Test(expected = ProductNotFoundException.class)
    public void purchaseProduct_NoValidParameter_ProductNotFoundException(){
        Long productId = new Long(1223334444L);
        BigDecimal total = new BigDecimal(24000);
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.empty());

        iyzicoPaymentService.pay(total);
        Mockito.when(purchasingRepository.save(Mockito.any())).thenReturn(purchasing);

        purchasingService.purchaseProduct(purchasingRequest);
    }
}
