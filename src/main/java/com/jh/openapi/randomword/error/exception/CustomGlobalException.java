package com.jh.openapi.randomword.error.exception;

import com.jh.openapi.randomword.error.dto.FailureResponse;
import com.jh.openapi.randomword.error.type.ErrorType;
import org.springframework.http.HttpStatus;

public interface CustomGlobalException {
    ErrorType error();

    default FailureResponse getFailureResponse() {
        return error().toFailureResponse();
    }

    default HttpStatus getStatus() {
        return error().getStatus();
    }

    default String getCode() {
        return error().getErrorTypeCode();
    }
}
