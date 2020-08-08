package com.iyzico.challenge.exception;

public class ProductNotFoundException extends BaseException{
    public ProductNotFoundException(){
        super(ExceptionCode.ProductNotFoundException);
    }
}
