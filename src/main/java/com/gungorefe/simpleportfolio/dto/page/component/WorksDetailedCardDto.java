package com.gungorefe.simpleportfolio.dto.page.component;

public record WorksDetailedCardDto(
        int id,
        String imageName,
        String title,
        String text,
        int displayOrder
) implements ComponentDto {
}
