package com.jh.openapi.randomword.utils;

import com.jh.openapi.randomword.error.exception.BusinessException;
import com.jh.openapi.randomword.error.type.ErrorType;
import com.jh.openapi.randomword.error.type.InternalServerErrorType;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

public class EnumUtil {
    /**
     * name을 통해 enumClass의 값 조회
     * errorType과 defaultValue는 반드시 하나의 값을 가져야 함
     *  - errorType == null && defaultValue == null 불가능
     *  - errorType != null && defaultValue != null 일 경우 defaultValue 우선처리
     * @param enumClass 반환 EnumClass 타입
     * @param name enum 상수 값 조회 키워드
     * @param defaultValue 조회 실패 반환 기본값
     * @param errorType 조회 실패 반환 Exception
     * @return 조회 성공 값
     * @param <T> enum
     */
    public static <T extends Enum<T>> T findByName(Class<T> enumClass, String name, Optional<T> defaultValue, Optional<ErrorType> errorType) {
        if (defaultValue.isEmpty() && errorType.isEmpty()) {
            throw new BusinessException(InternalServerErrorType.REQUIRED_PARAM_IS_OMITTED);
        }

        if (StringUtils.isEmpty(name)) {
            return throwExceptionIfPresentOrReturnDefault(errorType, defaultValue);
        }

        return Arrays.stream(enumClass.getEnumConstants())
                .filter(value -> value.name().equalsIgnoreCase(name))
                .findAny()
                .orElseGet(() -> throwExceptionIfPresentOrReturnDefault(errorType, defaultValue));
    }

    private static <T extends Enum<T>> T throwExceptionIfPresentOrReturnDefault(Optional<ErrorType> errorType, Optional<T> defaultValue) {
        if (defaultValue.isPresent()) {
            return defaultValue.get();
        }
        throw new BusinessException(errorType.get());
    }
}
