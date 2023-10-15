package com.jh.openapi.randomword.error.exception;

import com.jh.openapi.randomword.error.type.ErrorType;

public class BusinessException extends RuntimeException implements CustomGlobalException {
    private final ErrorType error;

    public BusinessException(ErrorType error) {
        super(error.getMessage());
        this.error = error;
    }

    @Override
    public ErrorType error() {
        return error;
    }
}
