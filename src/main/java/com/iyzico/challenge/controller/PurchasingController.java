package com.iyzico.challenge.controller;

import com.iyzico.challenge.dto.PurchasingRequest;
import com.iyzico.challenge.dto.PurchasingResponse;
import com.iyzico.challenge.service.PurchasingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    private PurchasingService purchasingService;

    @PostMapping("product")
    public ResponseEntity<PurchasingResponse> purchaseProduct(@RequestBody PurchasingRequest purchasingRequest, HttpServletRequest httpServletRequest){
        return new ResponseEntity<>(purchasingService.purchaseProduct(purchasingRequest), HttpStatus.CREATED);
    }
}
