package com.jh.openapi.randomword.validation.annotation;

import com.jh.openapi.randomword.domain.type.LanguageType;
import com.jh.openapi.randomword.validation.validator.LanguageValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LanguageValidator.class)
public @interface Language {
    LanguageType languageType() default LanguageType.ENG;
    String message() default "단어는 영어로 입력 되어야 합니다.";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default{};
}
