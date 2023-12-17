package com.gungorefe.simpleportfolio.dto.page.component;

public record CreateHomeSimpleCardRequest(
        String title,
        String text,
        int displayOrder
) implements CreateComponentRequest {
}
