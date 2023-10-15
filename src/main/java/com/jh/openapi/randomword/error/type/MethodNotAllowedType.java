package com.jh.openapi.randomword.error.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum MethodNotAllowedType implements ErrorType {
    METHOD_NOT_ALLOWED(0, "요청하신 URL에서 지원하지 않는 메서드입니다."),
    ;

    private final HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
    private final Integer number;
    private final String message;
}
