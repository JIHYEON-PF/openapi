package com.jh.openapi.randomword.validation.validator;

import com.jh.openapi.randomword.domain.entity.type.LanguageType;
import com.jh.openapi.randomword.validation.annotation.Language;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class LanguageValidator implements ConstraintValidator<Language, String> {
    private static final Pattern ENG_PATTERN = Pattern.compile("^[a-zA-Z]+$");
    private static final Pattern KOR_PATTERN = Pattern.compile("^[가-힇,\\s]+$");
    private LanguageType languageType;

    @Override
    public void initialize(Language parameter) {
        languageType = parameter.languageType();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            return false;
        }

        Pattern pattern = switch (languageType) {
            case ENG -> ENG_PATTERN;
            case KOR -> KOR_PATTERN;
        };

        return pattern.matcher(value).matches();
    }
}
