package com.jh.openapi.randomword.error.dto;

import com.jh.openapi.randomword.error.type.ErrorType;
import lombok.Getter;

@Getter
public class FailureResponse {
    private final String type;
    private final String message;

    public FailureResponse(final ErrorType errorType) {
        this.type = errorType.getErrorTypeCode();
        this.message = errorType.getMessage();
    }

    public FailureResponse(String errorTypeCode, String message) {
        this.type = errorTypeCode;
        this.message = message;
    }
}
