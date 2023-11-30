package com.jh.openapi.randomword.repository;

import com.jh.openapi.randomword.domain.entity.EnglishWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface WordRepository extends JpaRepository<EnglishWord, Long> {
    @Query(value = "SELECT WORD.idx, " +
                   "       WORD.vocabulary, " +
                   "       WORD.meaning, " +
                   "       WORD.level," +
                   "       WORD.reg_user_nickname, " +
                   "       WORD.reg_datetime, " +
                   "       WORD.mod_user_nickname, " +
                   "       WORD.mod_datetime " +
                   "FROM   openapi_db.english_word AS WORD " +
                   "WHERE  WORD.level IN :levelTypes " +
                   "ORDER BY RAND() " +
                   "LIMIT  :count", nativeQuery = true)
    List<EnglishWord> findAllOrderByRandomLimitBy(@Param("levelTypes") Set<String> levelTypes, @Param("count") Long count);

    Optional<EnglishWord> findEnglishWordByVocabulary(String vocabulary);
}
