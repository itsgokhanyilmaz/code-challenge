package com.iyzico.challenge.exception;

public class OutOfStockException extends BaseException{
    public OutOfStockException(){
        super(ExceptionCode.OutOfStockException);
    }
}
