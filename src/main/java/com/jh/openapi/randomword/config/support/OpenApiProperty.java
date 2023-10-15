package com.jh.openapi.randomword.config.support;

import com.jh.openapi.randomword.error.exception.BusinessException;
import com.jh.openapi.randomword.error.type.InternalServerErrorType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class OpenApiProperty {
    @Value("${naver.open-api.client.id.key}")
    private String clientIdKey;

    @Value("${naver.open-api.client.id.value}")
    private String clientIdValue;

    @Value("${naver.open-api.client.pw.key}")
    private String clientPwKey;

    @Value("${naver.open-api.client.pw.value}")
    private String clientPwValue;

    @Value("${naver.open-api.dictionary.path}")
    private String searchDictionaryApiPath;

    @Value("${naver.open-api.dictionary.method}")
    private String searchDictionaryApiMethod;

    public HttpMethod getOpenApiMethod() {
        if (StringUtils.isBlank(this.searchDictionaryApiMethod)) {
            throw new BusinessException(InternalServerErrorType.OPEN_API_HTTP_METHOD_IS_NULL);
        }

        return HttpMethod.resolve(this.searchDictionaryApiMethod);
    }
}
