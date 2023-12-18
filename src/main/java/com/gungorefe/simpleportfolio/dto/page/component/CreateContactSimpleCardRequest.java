package com.gungorefe.simpleportfolio.dto.page.component;

public record CreateContactSimpleCardRequest(
        String title,
        String text,
        int displayOrder
) implements CreateComponentRequest {
}
