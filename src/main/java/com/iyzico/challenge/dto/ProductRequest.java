package com.iyzico.challenge.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductRequest {

    @NotNull(message = "{product.name.notnull}")
    private String productName;

    @NotNull(message = "{description.notnull}")
    private String description;

    @NotNull(message = "{stock.count.notnull}")
    private Integer stockCount;

    @NotNull(message = "{price.notnull}")
    private BigDecimal price;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
