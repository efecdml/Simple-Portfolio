package com.gungorefe.simpleportfolio.vo;

public enum JwtToken {
    ACCESS_TOKEN("at");
    public final String value;

    JwtToken(String value) {
        this.value = value;
    }
}
