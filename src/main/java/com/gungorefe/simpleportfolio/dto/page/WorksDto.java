package com.gungorefe.simpleportfolio.dto.page;

public record WorksDto(
        String imageName,
        String title,
        String text
) implements PageDto {
}
