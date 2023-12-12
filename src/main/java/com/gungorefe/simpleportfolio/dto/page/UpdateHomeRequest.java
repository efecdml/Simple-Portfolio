package com.gungorefe.simpleportfolio.dto.page;

public record UpdateHomeRequest(
        String title,
        String text,
        String secondTitle,
        String secondText
) implements UpdatePageRequest {
}
