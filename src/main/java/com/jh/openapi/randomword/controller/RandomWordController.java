package com.jh.openapi.randomword.controller;

import com.jh.openapi.randomword.domain.entity.type.LanguageType;
import com.jh.openapi.randomword.domain.view.request.englishWord.WordRegisterRequestDto;
import com.jh.openapi.randomword.domain.view.request.englishWord.WordSearchRequestDto;
import com.jh.openapi.randomword.domain.view.request.englishWord.WordUpdateRequestDto;
import com.jh.openapi.randomword.domain.view.response.englishWord.WordResponsesDto;
import com.jh.openapi.randomword.domain.view.type.LanguageViewType;
import com.jh.openapi.randomword.error.type.BadRequestType;
import com.jh.openapi.randomword.service.RandomWordService;
import com.jh.openapi.randomword.utils.EnumUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RequiredArgsConstructor
@RequestMapping("/api/open-api")
@RestController
public class RandomWordController {
    private final RandomWordService randomWordService;

    @GetMapping("/random/word/{language}")
    public ResponseEntity<WordResponsesDto> getRandomWordByLevel(@PathVariable String language,
                                                                 @ModelAttribute WordSearchRequestDto requestDto) {

        LanguageType languageType = EnumUtil.findByName(LanguageViewType.class, language, Optional.empty(), Optional.of(BadRequestType.INVALID_LANGUAGE_TYPE))
                .convertToEntityType();

        return ResponseEntity.ok(randomWordService.getRandomWordByLevel(languageType, requestDto));
    }

    @PostMapping("/word/{language}")
    public ResponseEntity<Void> registerWord(@PathVariable String language,
                                             @RequestBody @Valid WordRegisterRequestDto requestDto) {

        LanguageType languageType = EnumUtil.findByName(LanguageViewType.class, language, Optional.empty(), Optional.of(BadRequestType.INVALID_LANGUAGE_TYPE))
                .convertToEntityType();

        randomWordService.registerWord(languageType, requestDto);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/word/{language}/{vocabulary}")
    public ResponseEntity<Void> updateWord(@PathVariable String language,
                                           @PathVariable String vocabulary,
                                           @RequestBody @Valid WordUpdateRequestDto requestDto) {

        LanguageType languageType = EnumUtil.findByName(LanguageViewType.class, language, Optional.empty(), Optional.of(BadRequestType.INVALID_LANGUAGE_TYPE))
                .convertToEntityType();

        randomWordService.updateWord(vocabulary, requestDto);

        return ResponseEntity.ok().build();
    }
}
