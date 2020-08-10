package com.iyzico.challenge.service.impl;

import com.iyzico.challenge.dto.PurchasingRequest;
import com.iyzico.challenge.dto.PurchasingResponse;
import com.iyzico.challenge.entity.Product;
import com.iyzico.challenge.entity.Purchasing;
import com.iyzico.challenge.exception.OutOfStockException;
import com.iyzico.challenge.exception.ProductNotFoundException;
import com.iyzico.challenge.mapper.PurchasingMapper;
import com.iyzico.challenge.repository.ProductRepository;
import com.iyzico.challenge.repository.PurchasingRepository;
import com.iyzico.challenge.service.IyzicoPaymentService;
import com.iyzico.challenge.service.PurchasingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
public class PurchasingServiceImpl implements PurchasingService {

    private Logger logger = LoggerFactory.getLogger(PurchasingServiceImpl.class);

    private PurchasingRepository purchasingRepository;
    private ProductRepository productRepository;
    private PurchasingMapper purchasingMapper;
    private IyzicoPaymentService iyzicoPaymentService;

    public PurchasingServiceImpl(PurchasingRepository purchasingRepository,
                                 ProductRepository productRepository,
                                 PurchasingMapper purchasingMapper,
                                 IyzicoPaymentService iyzicoPaymentService){

        this.purchasingRepository = purchasingRepository;
        this.productRepository = productRepository;
        this.purchasingMapper = purchasingMapper;
        this.iyzicoPaymentService = iyzicoPaymentService;
    }

    @Override
    public PurchasingResponse purchaseProduct(PurchasingRequest purchasingRequest) {
        Product product = productRepository.findById(purchasingRequest.getProductId()).orElseThrow(ProductNotFoundException::new);

        BigDecimal totalPrice = product.getPrice().multiply(new BigDecimal(purchasingRequest.getProductCount().toString()));
        iyzicoPaymentService.pay(totalPrice);
        decreaseStockCount(product, purchasingRequest.getProductCount());
        Purchasing mappedPurchasing = purchasingMapper.map.purchasingRequestToPurchasing(purchasingRequest);
        Purchasing purchasing = purchasingRepository.save(mappedPurchasing);
        return purchasingMapper.map.purchasingToPurchasingResponse(purchasing);
    }

    private synchronized void decreaseStockCount(Product product, Integer productCount){
        if (product.getStockCount() >= productCount){
            product.setStockCount(product.getStockCount() - productCount);
            productRepository.save(product);
            logger.info("Product id: {} has been deducted from stock, {} item(s)", product.getId(), productCount);
        }else{
            throw new OutOfStockException();
        }
    }
}
