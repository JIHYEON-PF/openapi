package com.jh.openapi.randomword.error.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum NotFoundType implements ErrorType {
    NOT_FOUND(0, "요청하신 URL을 찾을 수 없습니다."),
    CANNOT_FOUND_WORD(1, "단어를 찾을 수 없습니다."),
    ;

    private final HttpStatus status = HttpStatus.NOT_FOUND;
    private final Integer number;
    private final String message;
}
