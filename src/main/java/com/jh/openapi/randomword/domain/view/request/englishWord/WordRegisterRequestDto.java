package com.jh.openapi.randomword.domain.view.request.englishWord;

import com.jh.openapi.randomword.domain.entity.englishWord.EnglishWord;
import com.jh.openapi.randomword.domain.entity.type.LanguageType;
import com.jh.openapi.randomword.domain.view.type.WordLevelViewType;
import com.jh.openapi.randomword.error.type.BadRequestType;
import com.jh.openapi.randomword.utils.EnumUtil;
import com.jh.openapi.randomword.validation.annotation.Language;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
public class WordRegisterRequestDto {
    @NotBlank @Length(max = 100)
    @Language(languageType = LanguageType.ENG)
    private final String vocabulary;

    @NotBlank @Length(max = 100)
    @Language(languageType = LanguageType.KOR)
    private final String meaning;

    @NotNull
    private final WordLevelViewType level;

    @NotBlank
    private final String registerNickname;

    public WordRegisterRequestDto(String vocabulary, String meaning, String level, String registerNickname) {
        this.vocabulary = vocabulary.toLowerCase();
        this.meaning = meaning.replaceAll("\\s+", " ");
        this.level = EnumUtil.findByName(WordLevelViewType.class, level, Optional.empty(), Optional.of(BadRequestType.INVALID_WORD_LEVEL_TYPE));
        this.registerNickname = registerNickname;
    }

    public EnglishWord toEnglishWordEntity() {
        return EnglishWord.builder()
                .vocabulary(this.vocabulary)
                .meaning(this.meaning)
                .level(this.level.convertToEntityType())
                .regUserNickname(this.registerNickname)
                .regDatetime(LocalDateTime.now())
                .build();
    }
}
