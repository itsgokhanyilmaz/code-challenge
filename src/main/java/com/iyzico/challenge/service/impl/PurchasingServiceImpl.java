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
import com.iyzico.challenge.service.PurchasingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PurchasingServiceImpl implements PurchasingService {

    private PurchasingRepository purchasingRepository;
    private ProductRepository productRepository;
    private PurchasingMapper purchasingMapper;

    @Override
    public PurchasingResponse purchaseProduct(PurchasingRequest purchasingRequest) {
        Product product = productRepository.findById(purchasingRequest.getProductId()).orElseThrow(() -> new ProductNotFoundException());
        decreaseStockCount(product, purchasingRequest.getProductCount());
        //TODO: Payment will be added
        Purchasing purchasing = purchasingRepository.save(purchasingMapper.map.purchasingRequestToPurchasing(purchasingRequest));
        return purchasingMapper.map.purchasingToPurchasingResponse(purchasing);
    }

    private void decreaseStockCount(Product product, Integer productCount){
        if (product.getStockCount() >= productCount){
            product.setStockCount(product.getStockCount() - productCount);
            productRepository.save(product);
        }else{
            throw new OutOfStockException();
        }
    }
}
