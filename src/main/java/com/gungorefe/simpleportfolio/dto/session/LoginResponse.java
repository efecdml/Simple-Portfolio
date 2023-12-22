package com.gungorefe.simpleportfolio.dto.session;

import org.springframework.http.ResponseCookie;

public record LoginResponse(
        ResponseCookie cookie,
        String role
) {
}
