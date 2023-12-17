package com.gungorefe.simpleportfolio.dto.page.component;

public record UpdateWorksDetailedCardRequest(
        int id,
        String title,
        String text,
        int displayOrder
) implements UpdateComponentRequest {
}
