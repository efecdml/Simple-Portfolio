package com.gungorefe.simpleportfolio.dto.page;

public record UpdateWorksRequest(
        String title,
        String text
) implements UpdatePageRequest {
}
