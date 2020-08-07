package com.iyzico.challenge.service;

import com.iyzico.challenge.dto.PurchasingRequest;
import com.iyzico.challenge.dto.PurchasingResponse;

public interface PurchasingService {

    PurchasingResponse purchaseProduct(PurchasingRequest purchasingRequest);
}
