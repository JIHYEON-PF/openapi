package com.jh.openapi.randomword.domain.view.type;

import com.jh.openapi.randomword.domain.entity.type.LanguageType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LanguageViewType implements ViewTypeProvider {
    ENG("영문"),
    KOR("한글"),
    ;

    private final String description;

    @Override
    public LanguageType convertToEntityType() {
        return switch (this) {
            case ENG -> LanguageType.ENG;
            case KOR -> LanguageType.KOR;
        };
    }
}
