package com.jh.openapi.randomword.error.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ForbiddenType implements ErrorType {
    FORBIDDEN(0, "접근 권한이 없습니다."),
    CANNOT_ACCESS_RESOURCE(1, "접근 권한이 없습니다."),
    ;

    private final HttpStatus status = HttpStatus.FORBIDDEN;
    private final Integer number;
    private final String message;
}
