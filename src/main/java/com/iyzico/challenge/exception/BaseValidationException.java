package com.iyzico.challenge.exception;

import javax.validation.ConstraintDeclarationException;

public class BaseValidationException extends ConstraintDeclarationException {
    BaseValidationException(String code) {
        super(code);
    }
}

