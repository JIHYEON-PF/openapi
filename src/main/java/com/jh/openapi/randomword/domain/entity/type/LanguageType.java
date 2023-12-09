package com.jh.openapi.randomword.domain.entity.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum LanguageType {
    KOR("한글"),
    ENG("영어"),
    ;

    private final String description;
}
