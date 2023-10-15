package com.jh.openapi.randomword.repository;

import com.jh.openapi.randomword.domain.entity.EnglishWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<EnglishWord, String> {
    @Query(value = "SELECT * " +
                   "FROM   openapi_db.english_word AS word " +
                   "ORDER BY RAND() " +
                   "LIMIT  1", nativeQuery = true)
    EnglishWord findOneWordOrderByRandom();
}
