package com.gungorefe.simpleportfolio.dto.page.component;

public record CreateAboutSimpleCardRequest(
        String title,
        String text,
        int displayOrder
) implements CreateComponentRequest {
}
