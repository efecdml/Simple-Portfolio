package com.gungorefe.simpleportfolio.dto;

import org.springframework.http.MediaType;

public record Image(
        String name,
        MediaType mimeType,
        byte[] bytes
) {
}
