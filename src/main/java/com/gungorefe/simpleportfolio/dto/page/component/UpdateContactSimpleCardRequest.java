package com.gungorefe.simpleportfolio.dto.page.component;

public record UpdateContactSimpleCardRequest(
        int id,
        String title,
        String text,
        int displayOrder
) implements UpdateComponentRequest {
}
