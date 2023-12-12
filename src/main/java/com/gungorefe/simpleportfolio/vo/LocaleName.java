package com.gungorefe.simpleportfolio.vo;

import com.gungorefe.simpleportfolio.exception.ExceptionFactory;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum LocaleName {
    ENGLISH("en"),
    TURKISH("tr");
    public final String value;

    LocaleName(String value) {
        this.value = value;
    }

    private static final Set<String> VALUES = Arrays.stream(values())
            .map(v -> v.value)
            .collect(Collectors.toSet());

    public static void validate(String value) {
        if (!VALUES.contains(value)) {
            throw ExceptionFactory.getInvalidLocaleException(value);
        }
    }
}
