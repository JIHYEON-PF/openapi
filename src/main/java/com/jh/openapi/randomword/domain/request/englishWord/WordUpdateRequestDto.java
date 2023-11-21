package com.jh.openapi.randomword.domain.request.englishWord;

import com.jh.openapi.randomword.domain.type.LanguageType;
import com.jh.openapi.randomword.domain.type.WordLevelType;
import com.jh.openapi.randomword.validation.annotation.Language;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class WordUpdateRequestDto {
    @NotBlank @Length(max = 100)
    @Language(languageType = LanguageType.KOR)
    private final String meaning;

    @NotNull
    private final WordLevelType level;

    @NotBlank
    private final String updateNickname;

    public WordUpdateRequestDto(String meaning, String level, String updateNickname) {
        this.meaning = meaning;
        this.level = WordLevelType.findByName(level);
        this.updateNickname = updateNickname;
    }
}
