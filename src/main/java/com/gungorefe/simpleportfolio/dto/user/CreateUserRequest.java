package com.gungorefe.simpleportfolio.dto.user;

public record CreateUserRequest(
        String email,
        String password,
        String confirmedPassword,
        String role
) {
}
