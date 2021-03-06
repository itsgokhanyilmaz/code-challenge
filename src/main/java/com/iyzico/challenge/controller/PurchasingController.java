package com.iyzico.challenge.controller;

import com.iyzico.challenge.dto.PurchasingRequest;
import com.iyzico.challenge.dto.PurchasingResponse;
import com.iyzico.challenge.service.PurchasingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/purchasing")
public class PurchasingController {

    private PurchasingService purchasingService;

    public PurchasingController(PurchasingService purchasingService){
        this.purchasingService = purchasingService;
    }

    @PostMapping("product")
    public ResponseEntity<PurchasingResponse> purchaseProduct(@Valid @RequestBody PurchasingRequest purchasingRequest, HttpServletRequest httpServletRequest){

        PurchasingResponse purchasingResponse = purchasingService.purchaseProduct(purchasingRequest);
        return new ResponseEntity<>(purchasingResponse, HttpStatus.OK);
    }
}
