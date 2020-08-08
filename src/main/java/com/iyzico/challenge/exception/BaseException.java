package com.iyzico.challenge.exception;

public class BaseException extends RuntimeException {
    BaseException(String code){
        super(code);
    }
}
