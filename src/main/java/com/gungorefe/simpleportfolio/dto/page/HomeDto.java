package com.gungorefe.simpleportfolio.dto.page;

public record HomeDto(
        String title,
        String text,
        String secondTitle,
        String secondText
) implements PageDto {
}
