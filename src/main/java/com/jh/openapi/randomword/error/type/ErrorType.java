package com.jh.openapi.randomword.error.type;

import com.jh.openapi.randomword.error.dto.FailureResponse;
import org.springframework.http.HttpStatus;

public interface ErrorType {
    String SERVICE_NAME = "OPEN_API_JH";

    /**
     * 에러 유형에 따른 Http Method 조회
     * @return Exception HttpMethod
     */
    HttpStatus getStatus();

    /**
     * 에러 유형에 따른 고유 인덱스 번호 조회
     * @return Exception Index Number
     */
    Integer getNumber();

    /**
     * 발생하는 에러에 대한 상세 메시지
     * @return Exception Message
     */
    String getMessage();

    /**
     * 정의된 유형에 따른 에러 코드 반환
     * @return {{SERVICE_NAME}}-{{Error HttpMethod Value}}-{{Error Index Number}}
     */
    default String getErrorTypeCode() {
        return String.format("%s-%d-%03d", SERVICE_NAME, getStatus().value(), getNumber());
    }

    /**
     * 요청 처리 실패 응답 DTO를 반환한다.
     * @return 실패 Response DTO
     */
    default FailureResponse toFailureResponse() {
        return new FailureResponse(this);
    }
}
