package com.gungorefe.simpleportfolio.vo;

import java.util.regex.Pattern;

public enum RegexPattern {
    EMAIL("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$"), // OWASP
    PASSWORD("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,12}$"); // OWASP
    private final String value;

    RegexPattern(String value) {
        this.value = value;
    }

    public static boolean match(
            String s,
            RegexPattern pattern
    ) {
        return Pattern.compile(pattern.value)
                .matcher(s)
                .matches();
    }
}
