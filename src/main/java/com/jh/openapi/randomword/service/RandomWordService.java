package com.jh.openapi.randomword.service;

import com.jh.openapi.randomword.domain.entity.EnglishWord;
import com.jh.openapi.randomword.domain.request.englishWord.WordRegisterRequestDto;
import com.jh.openapi.randomword.domain.response.WordResponseDto;
import com.jh.openapi.randomword.domain.type.LanguageType;
import com.jh.openapi.randomword.domain.type.WordLevelType;
import com.jh.openapi.randomword.error.exception.BusinessException;
import com.jh.openapi.randomword.error.type.BadRequestType;
import com.jh.openapi.randomword.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RandomWordService {
    private final WordRepository wordRepository;

    public WordResponseDto getRandomWord(LanguageType languageType) {
        long wordCount = wordRepository.count();

        EnglishWord randomWord = getEnglishWord(wordCount);

        return WordResponseDto.fromEntity(randomWord);
    }

    private EnglishWord getEnglishWord(long wordCount) {
        Optional<EnglishWord> foundWord;
        do {
            long idx = new Random().nextLong(1, wordCount);

            foundWord = wordRepository.findById(idx);

        } while (foundWord.isEmpty());

        return foundWord.get();
    }

    public WordResponseDto getRandomWordByLevel(LanguageType language, WordLevelType level) {
        List<EnglishWord> englishWordWithLevel = wordRepository.findEnglishWordByLevel(level);

        int randomIndex = new Random().nextInt(0, englishWordWithLevel.size());

        return WordResponseDto.fromEntity(englishWordWithLevel.get(randomIndex));
    }

    @Transactional
    public void registerRandomWord(LanguageType language, WordRegisterRequestDto requestDto) {
        switch (language) {
            case ENG -> insertEnglishWord(requestDto);
            default -> throw new BusinessException(BadRequestType.INVALID_LANGUAGE_TYPE);
        }
    }

    private void insertEnglishWord(WordRegisterRequestDto requestDto) {
        EnglishWord englishWord = requestDto.toEnglishWordEntity();

        if (wordRepository.findEnglishWordByVocabulary(englishWord.getVocabulary()).isPresent()) {
            throw new BusinessException(BadRequestType.ALREADY_EXISTS_WORD);
        }

        wordRepository.save(englishWord);
    }
}
