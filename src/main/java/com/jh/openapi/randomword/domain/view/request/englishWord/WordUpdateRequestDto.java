package com.jh.openapi.randomword.domain.view.request.englishWord;

import com.jh.openapi.randomword.domain.entity.type.LanguageType;
import com.jh.openapi.randomword.domain.view.type.WordLevelViewType;
import com.jh.openapi.randomword.error.type.BadRequestType;
import com.jh.openapi.randomword.utils.EnumUtil;
import com.jh.openapi.randomword.validation.annotation.Language;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Getter
public class WordUpdateRequestDto {
    @NotBlank @Length(max = 100)
    @Language(languageType = LanguageType.KOR)
    private final String meaning;

    @NotNull
    private final WordLevelViewType level;

    @NotBlank
    private final String updateNickname;

    public WordUpdateRequestDto(String meaning, String level, String updateNickname) {
        this.meaning = meaning.replaceAll("\\s+", " ");
        this.level = EnumUtil.findByName(WordLevelViewType.class, level, Optional.empty(), Optional.of(BadRequestType.INVALID_WORD_LEVEL_TYPE));
        this.updateNickname = updateNickname;
    }
}
