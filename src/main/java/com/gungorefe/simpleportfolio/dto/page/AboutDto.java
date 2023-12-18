package com.gungorefe.simpleportfolio.dto.page;

public record AboutDto(
        String imageName,
        String title,
        String text
) implements PageDto {
}
