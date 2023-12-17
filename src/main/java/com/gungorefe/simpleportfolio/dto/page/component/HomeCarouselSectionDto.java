package com.gungorefe.simpleportfolio.dto.page.component;

public record HomeCarouselSectionDto(
        int id,
        String imageName,
        String text,
        int displayOrder
) implements ComponentDto {
}
