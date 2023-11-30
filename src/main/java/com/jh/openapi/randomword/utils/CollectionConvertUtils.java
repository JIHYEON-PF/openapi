package com.jh.openapi.randomword.utils;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectionConvertUtils {
    public static Set<String> convertEnumerateToNameSet(EnumSet<?> enumerations) {
        return enumerations.stream()
                .map(Enum::name)
                .collect(Collectors.toSet());
    }
}
