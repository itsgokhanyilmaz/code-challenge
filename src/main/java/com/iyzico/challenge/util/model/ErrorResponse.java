package com.iyzico.challenge.util.model;


public class ErrorResponse {

    private String message;

    public ErrorResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }

    private String code;

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

}
