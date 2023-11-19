package com.jh.openapi.randomword.domain.request.englishWord;

import com.jh.openapi.randomword.domain.entity.EnglishWord;
import com.jh.openapi.randomword.domain.type.WordLevelType;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class WordRegisterRequestDto {
    @NotBlank @Length(max = 100)
    private final String vocabulary;

    @NotBlank @Length(max = 100)
    private final String meaning;

    @NotNull
    private final WordLevelType level;

    private final String registerNickname;

    public WordRegisterRequestDto(String vocabulary, String meaning, String level, String registerNickname) {
        this.vocabulary = vocabulary;
        this.meaning = meaning;
        this.level = WordLevelType.findByName(level);
        this.registerNickname = registerNickname;
    }

    public EnglishWord toEnglishWordEntity() {
        return EnglishWord.builder()
                .vocabulary(this.vocabulary)
                .meaning(this.meaning)
                .level(this.level)
                .regUserNickname(this.registerNickname)
                .regDatetime(LocalDateTime.now())
                .build();
    }
}
