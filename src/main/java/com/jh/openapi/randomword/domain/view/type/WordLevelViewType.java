package com.jh.openapi.randomword.domain.view.type;

import com.jh.openapi.randomword.domain.entity.type.WordLevelType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WordLevelViewType implements ViewTypeProvider {
    ALL("전체 레벨"),
    ELEMENT("초등"),
    MIDDLE("중등"),
    HIGH("고등"),
    ;

    private final String description;


    @Override
    public WordLevelType convertToEntityType() {
        return switch (this) {
            case ALL -> null;
            case ELEMENT -> WordLevelType.ELEMENT;
            case MIDDLE -> WordLevelType.MIDDLE;
            case HIGH -> WordLevelType.HIGH;
        };
    }
}
