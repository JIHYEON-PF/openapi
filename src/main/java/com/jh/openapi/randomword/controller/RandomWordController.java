package com.jh.openapi.randomword.controller;

import com.jh.openapi.randomword.domain.response.WordResponseDto;
import com.jh.openapi.randomword.domain.type.LanguageType;
import com.jh.openapi.randomword.service.RandomWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jh.openapi.randomword.domain.type.LanguageType.findByName;

@RequiredArgsConstructor
@RequestMapping("/api/open-api")
@RestController
public class RandomWordController {
    private final RandomWordService randomWordService;

    @GetMapping("/random/word/{language}")
    public ResponseEntity<WordResponseDto> getRandomWord(@PathVariable String language) {
        LanguageType languageType = findByName(language);

        return ResponseEntity.ok(randomWordService.getRandomWord(languageType));
    }
}
