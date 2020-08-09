package com.iyzico.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class PurchasingRequest {

    private Long productId;

    private Integer productCount;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }
}
