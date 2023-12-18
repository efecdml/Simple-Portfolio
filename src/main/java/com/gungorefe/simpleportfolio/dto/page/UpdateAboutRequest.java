package com.gungorefe.simpleportfolio.dto.page;

public record UpdateAboutRequest(
        String title,
        String text
) implements UpdatePageRequest {
}
