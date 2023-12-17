package com.gungorefe.simpleportfolio.dto.page.component;

public record UpdateHomeCarouselSectionRequest(
        int id,
        String text,
        int displayOrder
) implements UpdateComponentRequest {
}
