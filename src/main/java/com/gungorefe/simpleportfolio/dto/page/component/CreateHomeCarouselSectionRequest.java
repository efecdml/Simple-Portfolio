package com.gungorefe.simpleportfolio.dto.page.component;

public record CreateHomeCarouselSectionRequest(
        String text,
        int displayOrder
) implements CreateComponentRequest {
}
