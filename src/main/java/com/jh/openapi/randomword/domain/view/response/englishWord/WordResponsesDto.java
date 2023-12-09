package com.jh.openapi.randomword.domain.view.response.englishWord;

import lombok.Getter;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WordResponsesDto {
    private final List<WordResponseDto> words = new ArrayList<>();

    public static WordResponsesDto from(List<WordResponseDto> words) {
        return new WordResponsesDto(words);
    }

    public static WordResponsesDto empty() {
        return new WordResponsesDto(List.of(WordResponseDto.emptyWord()));
    }

    private WordResponsesDto(List<WordResponseDto> words) {
        if (BooleanUtils.isFalse(CollectionUtils.isEmpty(words))) {
            this.words.addAll(words);
        }
    }
}
