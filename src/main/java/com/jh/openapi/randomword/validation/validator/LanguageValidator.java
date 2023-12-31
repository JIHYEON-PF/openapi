package com.jh.openapi.randomword.validation.validator;

import com.jh.openapi.randomword.domain.entity.type.LanguageType;
import com.jh.openapi.randomword.validation.annotation.Language;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class LanguageValidator implements ConstraintValidator<Language, String> {
    private static final Pattern ENG_PATTERN = Pattern.compile("^[a-zA-Z]+$");
    private static final Pattern KOR_PATTERN = Pattern.compile("^[가-힣,\\s]+$");
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

        boolean matches = pattern.matcher(value).matches();

        if (BooleanUtils.isFalse(matches)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(languageType.getDescription() + "로 입력 되어야 합니다.")
                    .addConstraintViolation();
        }

        return matches;
    }
}
