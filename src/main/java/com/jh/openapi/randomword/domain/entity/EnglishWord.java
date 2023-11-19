package com.jh.openapi.randomword.domain.entity;

import com.jh.openapi.randomword.domain.entity.metadata.MetaDataEntity;
import com.jh.openapi.randomword.domain.type.WordLevelType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "english_word")
public class EnglishWord extends MetaDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "vocabulary", nullable = false, length = 100)
    private String vocabulary;

    @Column(name = "meaning", nullable = false, length = 100)
    private String meaning;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    private WordLevelType level;

    @Builder
    public EnglishWord(Long idx, String vocabulary, String meaning, WordLevelType level, String regUserNickname, LocalDateTime regDatetime, String modUserNickname, LocalDateTime modDatetime) {
        this.idx = idx;
        this.vocabulary = vocabulary;
        this.meaning = meaning;
        this.level = level;
        this.regUserNickname = regUserNickname;
        this.regDatetime = regDatetime;
        this.modUserNickname = modUserNickname;
        this.modDatetime = modDatetime;
    }

    public void updateMeaning(String meaning) {
        this.meaning = meaning;
    }
}
