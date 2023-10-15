package com.jh.openapi.randomword.domain.entity;

import com.jh.openapi.randomword.domain.entity.metadata.MetaDataEntity;
import com.jh.openapi.randomword.domain.type.WordLevel;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "english_word")
public class EnglishWord extends MetaDataEntity {
    @Id
    @Column(name = "vocabulary", nullable = false, length = 100)
    private String vocabulary;

    @Column(name = "meaning", nullable = false, length = 100)
    private String meaning;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    private WordLevel level;

    public void updateMeaning(String meaning) {
        this.meaning = meaning;
    }
}
