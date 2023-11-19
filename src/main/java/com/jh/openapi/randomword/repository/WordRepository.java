package com.jh.openapi.randomword.repository;

import com.jh.openapi.randomword.domain.entity.EnglishWord;
import com.jh.openapi.randomword.domain.type.WordLevelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<EnglishWord, Long> {
    List<EnglishWord> findEnglishWordByLevel(WordLevelType level);

    Optional<EnglishWord> findEnglishWordByVocabulary(String vocabulary);
}
