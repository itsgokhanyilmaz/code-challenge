package com.iyzico.challenge.service.impl;

import com.iyzico.challenge.dto.PurchasingRequest;
import com.iyzico.challenge.dto.PurchasingResponse;
import com.iyzico.challenge.repository.PurchasingRepository;
import com.iyzico.challenge.service.PurchasingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchasingServiceImpl implements PurchasingService {

    private final PurchasingRepository purchasingRepository;

    @Override
    public PurchasingResponse purchaseProduct(PurchasingRequest purchasingRequest) {
        return null;
    }
}
