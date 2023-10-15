package com.jh.openapi.randomword.service;

import com.jh.openapi.randomword.domain.entity.EnglishWord;
import com.jh.openapi.randomword.domain.entity.User;
import com.jh.openapi.randomword.domain.response.WordResponseDto;
import com.jh.openapi.randomword.domain.type.LanguageType;
import com.jh.openapi.randomword.repository.UserRepository;
import com.jh.openapi.randomword.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RandomWordService {
    private final WordRepository wordRepository;
    private final UserRepository userRepository;

    public WordResponseDto getRandomWord(LanguageType languageType) {
        EnglishWord randomWord = wordRepository.findOneWordOrderByRandom();

        String nickName = userRepository.findById(randomWord.getLastUpdateUserIdx())
                .map(User::getNickName)
                .orElse(null);

        return WordResponseDto.fromEntity(randomWord, nickName);
    }
}
