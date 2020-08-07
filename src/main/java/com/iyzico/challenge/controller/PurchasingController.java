package com.iyzico.challenge.controller;

import com.iyzico.challenge.dto.MainResponse;
import com.iyzico.challenge.dto.PurchasingRequest;
import com.iyzico.challenge.dto.PurchasingResponse;
import com.iyzico.challenge.service.PurchasingService;
import com.iyzico.challenge.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/purchasing")
@RequiredArgsConstructor
public class PurchasingController {

    private final PurchasingService purchasingService;

    @PostMapping("product")
    public ResponseEntity<MainResponse<PurchasingResponse>> purchaseProduct(@RequestBody PurchasingRequest purchasingRequest, HttpServletRequest httpServletRequest){
        return ResponseUtil.data(purchasingService.purchaseProduct(purchasingRequest));
    }
}
