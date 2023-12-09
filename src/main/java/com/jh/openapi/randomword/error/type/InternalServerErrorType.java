package com.jh.openapi.randomword.error.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum InternalServerErrorType implements ErrorType {
    INTERNAL_SERVER_ERROR(0, "시스템 오류가 발생했습니다."),
    OPEN_API_HTTP_METHOD_IS_NULL(1, "OpenAPI를 호출할 HttpMethod가 없습니다."),
    REQUIRED_PARAM_IS_OMITTED(2, "함수 호출에 필수 요청 파라미터가 누락되었습니다."),
    ;

    private final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    private final Integer number;
    private final String message;
}
