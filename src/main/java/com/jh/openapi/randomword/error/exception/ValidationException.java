package com.jh.openapi.randomword.error.exception;

import com.jh.openapi.randomword.error.dto.FailureResponse;
import com.jh.openapi.randomword.error.type.BadRequestType;
import com.jh.openapi.randomword.error.type.ErrorType;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;

import java.util.List;

public class ValidationException extends RuntimeException implements CustomGlobalException {
    private static final ErrorType ERROR_TYPE = BadRequestType.FAILED_TO_VALIDATE_REQUEST;
    private final List<FieldError> fieldErrors;

    public ValidationException(List<FieldError> fieldErrors) {
        super(ERROR_TYPE.getMessage());
        this.fieldErrors = fieldErrors;
    }

    @Override
    public ErrorType error() {
        return ERROR_TYPE;
    }

    @Override
    public String getMessage() {
        if (CollectionUtils.isEmpty(fieldErrors)) {
            return ERROR_TYPE.getMessage();
        }

        final FieldError firstError = fieldErrors.get(0);
        if (fieldErrors.size() == 1) {
            return String.format("%s은(는) %s", firstError.getField(), firstError.getDefaultMessage());
        }

        return String.format("%s외 %d개의 잘못된 형식의 요청 데이터가 존재합니다.", firstError.getField(), fieldErrors.size() - 1);
    }

    @Override
    public FailureResponse getFailureResponse() {
        return new FailureResponse(ERROR_TYPE.getErrorTypeCode(), getMessage());
    }
}
