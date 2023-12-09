package com.jh.openapi.randomword.service;

import com.jh.openapi.randomword.domain.entity.englishWord.EnglishWord;
import com.jh.openapi.randomword.domain.view.request.englishWord.WordRegisterRequestDto;
import com.jh.openapi.randomword.domain.view.request.englishWord.WordSearchRequestDto;
import com.jh.openapi.randomword.domain.view.request.englishWord.WordUpdateRequestDto;
import com.jh.openapi.randomword.domain.view.response.englishWord.WordResponseDto;
import com.jh.openapi.randomword.domain.view.response.englishWord.WordResponsesDto;
import com.jh.openapi.randomword.domain.entity.type.LanguageType;
import com.jh.openapi.randomword.domain.entity.type.WordLevelType;
import com.jh.openapi.randomword.error.exception.BusinessException;
import com.jh.openapi.randomword.error.type.BadRequestType;
import com.jh.openapi.randomword.error.type.NotFoundType;
import com.jh.openapi.randomword.repository.WordRepository;
import com.jh.openapi.randomword.utils.CollectionConvertUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RandomWordService {
    private final WordRepository wordRepository;

    public WordResponsesDto getRandomWord(LanguageType languageType, Long count) {
        EnumSet<?> levelTypes = EnumSet.of(WordLevelType.ELEMENT, WordLevelType.MIDDLE, WordLevelType.HIGH);
        List<EnglishWord> words = wordRepository.findAllOrderByRandomLimitBy(CollectionConvertUtils.convertEnumerateToNameSet(levelTypes), count);

        return createWordResponsesDtoFromEntityList(words);
    }

    public WordResponsesDto getRandomWordByLevel(LanguageType language, WordSearchRequestDto requestDto) {
        Set<String> levelNameSet = CollectionConvertUtils.convertEnumerateToNameSet(requestDto.convertRequestEnumToEntityEnumSet());
        Long count = requestDto.getCount();

        List<EnglishWord> words = wordRepository.findAllOrderByRandomLimitBy(levelNameSet, count);

        return createWordResponsesDtoFromEntityList(words);
    }

    private WordResponsesDto createWordResponsesDtoFromEntityList(List<EnglishWord> words) {
        if (CollectionUtils.isEmpty(words)) {
            return WordResponsesDto.empty();
        }

        return WordResponsesDto.from(words.stream().map(WordResponseDto::fromEntity).collect(Collectors.toList()));
    }

    @Transactional
    public void registerWord(LanguageType language, WordRegisterRequestDto requestDto) {
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

    @Transactional
    public void updateWord(String vocabulary, WordUpdateRequestDto requestDto) {
        EnglishWord targetWord = wordRepository.findEnglishWordByVocabulary(vocabulary)
                .orElseThrow(() -> new BusinessException(NotFoundType.CANNOT_FOUND_WORD));

        targetWord.update(requestDto.getMeaning(), requestDto.getLevel().convertToEntityType(), requestDto.getUpdateNickname());
    }
}
