package com.iyzico.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchasingResponse {

    private Long productId;

    private Integer productCount;
}
