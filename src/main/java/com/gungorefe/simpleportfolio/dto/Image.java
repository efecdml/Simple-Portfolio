package com.gungorefe.simpleportfolio.dto;

import org.springframework.http.MediaType;

public record Image(
        MediaType mimeType,
        byte[] bytes
) {
}
