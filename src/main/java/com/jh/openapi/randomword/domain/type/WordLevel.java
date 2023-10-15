package com.jh.openapi.randomword.domain.type;

import com.jh.openapi.randomword.error.exception.BusinessException;
import com.jh.openapi.randomword.error.type.BadRequestType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum WordLevel {
    ELEMENT,
    MIDDLE,
    HIGH,
    ;

    public static WordLevel findByName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new BusinessException(BadRequestType.INVALID_WORD_TYPE);
        }

        return Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(() -> new BusinessException(BadRequestType.INVALID_WORD_TYPE));
    }
}
