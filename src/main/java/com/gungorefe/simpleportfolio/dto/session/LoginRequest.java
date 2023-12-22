package com.gungorefe.simpleportfolio.dto.session;

public record LoginRequest(
        String email,
        String password
) {
}
