package com.jh.openapi.randomword.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jh.openapi.randomword.domain.entity.EnglishWord;
import com.jh.openapi.randomword.domain.type.WordLevel;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Getter
public class WordResponseDto {
    private final String vocabulary;
    private final String mean;
    private final WordLevel level;
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm")
    private final LocalDateTime lastUpdateDatetime;
    private final String lastUpdateUserNickName;

    private static final String UNDEFINED_USER = "알 수 없는 사용자";

    public static WordResponseDto fromEntity(EnglishWord entity, String nickName) {
        Assert.notNull(entity, "entity must not be null");

        return WordResponseDto.builder()
                .vocabulary(entity.getVocabulary())
                .mean(entity.getMeaning())
                .level(entity.getLevel())
                .lastUpdateDatetime(entity.getLastUpdateDatetime())
                .lastUpdateUserNickName(getUserNickName(nickName))
                .build();
    }

    @Builder
    public WordResponseDto(String vocabulary, String mean, WordLevel level, LocalDateTime lastUpdateDatetime, String lastUpdateUserNickName) {
        this.vocabulary = vocabulary;
        this.mean = mean;
        this.level = level;
        this.lastUpdateDatetime = lastUpdateDatetime;
        this.lastUpdateUserNickName = lastUpdateUserNickName;
    }

    private static String getUserNickName(String nickName) {
        if (StringUtils.isNotBlank(nickName)) {
            return nickName;
        }

        return UNDEFINED_USER;
    }
}
