package com.jh.openapi.randomword.domain.view.request.englishWord;

import com.jh.openapi.randomword.domain.entity.type.WordLevelType;
import com.jh.openapi.randomword.domain.view.type.WordLevelViewType;
import com.jh.openapi.randomword.utils.EnumUtil;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Optional;

@Getter
public class WordSearchRequestDto {
    private final WordLevelViewType wordLevelViewType;
    private final Long count;

    public WordSearchRequestDto(String level, Long count) {
        this.wordLevelViewType = EnumUtil.findByName(WordLevelViewType.class, level, Optional.of(WordLevelViewType.ALL),Optional.empty());
        this.count = validateCount(count);
    }

    private Long validateCount(Long count) {
        if (Objects.isNull(count) || count == 0) {
            return 1L;
        }
        return count;
    }

    public EnumSet<WordLevelType> convertRequestEnumToEntityEnumSet() {
        return switch (this.wordLevelViewType) {
            case ALL -> EnumSet.of(WordLevelType.ELEMENT, WordLevelType.MIDDLE, WordLevelType.HIGH);
            case ELEMENT -> EnumSet.of(WordLevelType.ELEMENT);
            case MIDDLE -> EnumSet.of(WordLevelType.MIDDLE);
            case HIGH -> EnumSet.of(WordLevelType.HIGH);
        };
    }
}
