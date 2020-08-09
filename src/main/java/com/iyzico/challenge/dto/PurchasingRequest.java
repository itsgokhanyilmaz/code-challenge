package com.iyzico.challenge.dto;

import javax.validation.constraints.NotNull;

public class PurchasingRequest {

    @NotNull(message = "{product.id.notnull}")
    private Long productId;

    @NotNull(message = "{product.count.notnull}")
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
