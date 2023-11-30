package com.jh.openapi.randomword.controller;

import com.jh.openapi.randomword.domain.request.englishWord.WordRegisterRequestDto;
import com.jh.openapi.randomword.domain.request.englishWord.WordUpdateRequestDto;
import com.jh.openapi.randomword.domain.response.WordResponsesDto;
import com.jh.openapi.randomword.domain.type.LanguageType;
import com.jh.openapi.randomword.domain.type.WordLevelType;
import com.jh.openapi.randomword.service.RandomWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RequestMapping("/api/open-api")
@RestController
public class RandomWordController {
    private final RandomWordService randomWordService;

    @GetMapping("/random/word/{language}")
    public ResponseEntity<WordResponsesDto> getRandomWord(@PathVariable String language,
                                                          @RequestParam(defaultValue = "1") Long count) {
        LanguageType languageType = LanguageType.findByName(language);

        return ResponseEntity.ok(randomWordService.getRandomWord(languageType, count));
    }

    @GetMapping("/random/word/{language}/{level}")
    public ResponseEntity<WordResponsesDto> getRandomWordByLevel(@PathVariable String language,
                                                                      @PathVariable String level,
                                                                      @RequestParam(defaultValue = "1") Long count) {
        LanguageType languageType = LanguageType.findByName(language);
        WordLevelType wordLevelType = WordLevelType.findByName(level);

        return ResponseEntity.ok(randomWordService.getRandomWordByLevel(languageType, wordLevelType, count));
    }

    @PostMapping("/word/{language}")
    public ResponseEntity<Void> registerWord(@PathVariable String language,
                                             @RequestBody @Valid WordRegisterRequestDto requestDto) {

        LanguageType languageType = LanguageType.findByName(language);

        randomWordService.registerWord(languageType, requestDto);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/word/{language}/{vocabulary}")
    public ResponseEntity<Void> updateWord(@PathVariable String language,
                                           @PathVariable String vocabulary,
                                           @RequestBody @Valid WordUpdateRequestDto requestDto) {

        LanguageType languageType = LanguageType.findByName(language);

        randomWordService.updateWord(vocabulary, requestDto);

        return ResponseEntity.ok().build();
    }
}
